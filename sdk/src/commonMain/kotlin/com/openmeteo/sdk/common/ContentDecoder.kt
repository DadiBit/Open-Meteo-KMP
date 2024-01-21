package com.openmeteo.sdk.common

import com.google.flatbuffers.kotlin.ArrayReadWriteBuffer
import com.google.flatbuffers.kotlin.ReadWriteBuffer
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsChannel
import io.ktor.client.statement.bodyAsText
import io.ktor.utils.io.ByteReadChannel
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.protobuf.ProtoBuf

/**
 * A "mixin" class that joins kotlinx.serialization and google flatbuffers generated
 * code.
 *
 * You can omit only one of the two params, when you intend to use it for
 * decoding just with the other. Trying to use a format that requires the parameter
 * that wasn't provided will result in an [IllegalArgumentException].
 * @param serializer The KSerializer used for decoding Json, ProtoBuf and similar
 * @param asRoot The function used to decode FlatBuffer data
 */
class ContentDecoder<R>(
    private val serializer: KSerializer<R>? = null,
    asRoot: ((buffer: ReadWriteBuffer) -> R)? = null,
) {

    init {
        require(serializer != null || asRoot != null) {
            "Please provide at least a non-null serializer or a non-null asRoot decoder"
        }
    }

    private val flatBufferDecoder = asRoot?.let { FlatBufferDecoder(it) }

    /**
     * Given an HTTP response, get its body, parse it using the given format.
     * A list is always returned. If the data is not a list then it's decoded
     * and inserted as the only element of the list.
     * @param res The KTOR HTTP response (not checked if successful)
     * @param format The body format (ie: Json, ProtoBuf, FlatBuffers)
     * @param asList If true, use [ListSerializer] or
     * [FlatBufferDecoder.decodeListFromByteChannel], depending on the type
     */
    suspend fun decode(
        res: HttpResponse,
        format: ContentFormat = ContentFormat.Json,
        asList: Boolean = format == ContentFormat.FlatBuffers
    ) = if (asList) decodeList(res, format)
        else listOf(decodeSingle(res, format))

    private suspend fun decodeList(
        res: HttpResponse,
        format: ContentFormat = ContentFormat.Json,
    ) = decode(res, format,
        serializer?.let { ListSerializer(it) },
        flatBufferDecoder?.let { it::decodeListFromByteChannel }
    )

    private suspend fun decodeSingle(
        res: HttpResponse,
        format: ContentFormat = ContentFormat.Json,
    ) = decode(res, format, serializer, flatBufferDecoder?.let { it::decodeFromByteChannel })

    companion object {

        suspend fun <T> decode(
            res: HttpResponse,
            format: ContentFormat = ContentFormat.Json,
            serializer: KSerializer<T>? = null,
            decoder: (suspend (ByteReadChannel) -> T)? = null,
        ) = when(format) {
            ContentFormat.Json -> decodeJson(requireNotNull(serializer), res)
            ContentFormat.ProtoBuf -> decodeProtoBuf(requireNotNull(serializer), res)
            ContentFormat.FlatBuffers -> decodeFlatBuffers(requireNotNull(decoder), res)
        }

        fun <T> decodeJson(
            serializer: KSerializer<T>,
            string: String,
        ) = Json.decodeFromString(serializer, string)

        suspend fun <T> decodeJson(
            serializer: KSerializer<T>,
            res: HttpResponse,
        ) = decodeJson(serializer, res.bodyAsText())

        @OptIn(ExperimentalSerializationApi::class)
        fun <T> decodeProtoBuf(
            serializer: KSerializer<T>,
            byteArray: ByteArray,
        ) = ProtoBuf.decodeFromByteArray(serializer, byteArray)

        suspend fun <T> decodeProtoBuf(
            serializer: KSerializer<T>,
            res: HttpResponse,
        ) = decodeProtoBuf(serializer, res.body<ByteArray>())

        suspend fun <T> decodeFlatBuffers(
            decodeFromByteChannel: suspend (ByteReadChannel) -> T,
            res: HttpResponse,
        ) = decodeFromByteChannel(res.bodyAsChannel())

    }

}

public suspend fun HttpResponse.bodyAsBuffer() : ArrayReadWriteBuffer =
    ArrayReadWriteBuffer(this.body<ByteArray>())
