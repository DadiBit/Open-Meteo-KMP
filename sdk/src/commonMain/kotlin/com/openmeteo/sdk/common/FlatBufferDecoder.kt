package com.openmeteo.sdk.common

import com.google.flatbuffers.kotlin.ArrayReadWriteBuffer
import com.google.flatbuffers.kotlin.ReadWriteBuffer
import io.ktor.util.toByteArray
import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.core.readBytes
import io.ktor.utils.io.readIntLittleEndian

/**
 * A FlatBuffer helper class that eases root size-prefixed Arrays decoding.
 * @param asRoot The asRoot method reference used to decode the binary data.
 */
internal open class FlatBufferDecoder<R>(
    internal val asRoot: (buffer: ReadWriteBuffer) -> R,
) {
    fun decodeFromArrayBuffer(arrayBuffer: ArrayReadWriteBuffer) =
        asRoot(arrayBuffer)

    fun decodeFromByteArray(byteArray: ByteArray) =
        asRoot(ArrayReadWriteBuffer(byteArray))

    suspend fun decodeFromByteChannel(byteChannel: ByteReadChannel) =
        decodeFromByteArray(byteChannel.toByteArray())

    /**
     * Call [block] on each size-prefixed array entry.
     */
    suspend fun streamListFromByteChannel(
        byteChannel: ByteReadChannel,
        block: (R) -> Unit
    ) {
        // by looping we avoid loading the whole channel into memory
        while (!byteChannel.isClosedForRead) {
            val size = byteChannel.readIntLittleEndian()
            // Read a packet of `size` bytes, convert it to a ByteArray
            val bytes = byteChannel.readPacket(size).readBytes()
            // TODO: catch closed stream/not enough data error -> more comprehensive error?
            // Parse the buffer as the object root
            val buffer = ArrayReadWriteBuffer(bytes)
            val root = decodeFromArrayBuffer(buffer)
            block(root)
        }
    }

    /**
     * Build a [List] from a [ByteReadChannel] holding a size-prefixed array of
     * root type entries.
     */
    suspend fun decodeListFromByteChannel(byteChannel: ByteReadChannel) =
        buildList { streamListFromByteChannel(byteChannel, ::add) }
}
