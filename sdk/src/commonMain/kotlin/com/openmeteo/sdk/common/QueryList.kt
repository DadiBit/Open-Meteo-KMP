package com.openmeteo.sdk.common

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

typealias QueryList<T> = @Serializable(QueryListSerializer::class) List<T>

class QueryListSerializer<T>(
    private val dataSerializer: KSerializer<T>
) : KSerializer<List<T>> {

    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("QueryCollection<T>", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): List<T> =
        TODO("Deserialization not (yet) implemented")

    override fun serialize(encoder: Encoder, value: List<T>) =
        encoder.encodeString(value.joinToString(","))

}
