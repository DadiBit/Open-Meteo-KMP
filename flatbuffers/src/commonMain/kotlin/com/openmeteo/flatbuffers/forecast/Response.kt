// automatically generated by the FlatBuffers compiler, do not modify

package com.openmeteo.flatbuffers.forecast

import com.google.flatbuffers.kotlin.*
import kotlin.jvm.JvmInline
@Suppress("unused")
class Response : Table() {

    fun init(i: Int, buffer: ReadWriteBuffer) : Response = reset(i, buffer)

    val latitude : Float get() = lookupField(4, 0.0f ) { bb.getFloat(it + bufferPos) }

    val longitude : Float get() = lookupField(6, 0.0f ) { bb.getFloat(it + bufferPos) }

    val elevation : Float get() = lookupField(8, 0.0f ) { bb.getFloat(it + bufferPos) }

    val generationTimeMilliseconds : Float get() = lookupField(10, 0.0f ) { bb.getFloat(it + bufferPos) }

    val locationId : Long get() = lookupField(12, 0L ) { bb.getLong(it + bufferPos) }

    val model : com.openmeteo.flatbuffers.forecast.Model get() = lookupField(14, com.openmeteo.flatbuffers.forecast.Model(0u) ) { com.openmeteo.flatbuffers.forecast.Model(bb.getUByte(it + bufferPos)) }

    val utcOffsetSeconds : Int get() = lookupField(16, 0 ) { bb.getInt(it + bufferPos) }

    val timezone : String? get() = lookupField(18, null ) { string(it + bufferPos) }
    fun timezoneAsBuffer() : ReadBuffer = vectorAsBuffer(bb, 18, 1)

    val timezoneAbbreviation : String? get() = lookupField(20, null ) { string(it + bufferPos) }
    fun timezoneAbbreviationAsBuffer() : ReadBuffer = vectorAsBuffer(bb, 20, 1)

    fun timeframes(j: Int) : com.openmeteo.flatbuffers.forecast.Timeframe? = timeframes(com.openmeteo.flatbuffers.forecast.Timeframe(), j)
    fun timeframes(obj: com.openmeteo.flatbuffers.forecast.Timeframe, j: Int) : com.openmeteo.flatbuffers.forecast.Timeframe? = lookupField(32, null ) { obj.init(indirect(vector(it) + j * 4), bb) }
    val timeframesLength : Int get() = lookupField(32, 0 ) { vectorLength(it) }

    companion object {
        fun validateVersion() = VERSION_2_0_8

        fun asRoot(buffer: ReadWriteBuffer) : Response = asRoot(buffer, Response())
        fun asRoot(buffer: ReadWriteBuffer, obj: Response) : Response = obj.init(buffer.getInt(buffer.limit) + buffer.limit, buffer)


        fun createResponse(builder: FlatBufferBuilder, latitude: Float, longitude: Float, elevation: Float, generationTimeMilliseconds: Float, locationId: Long, model: com.openmeteo.flatbuffers.forecast.Model, utcOffsetSeconds: Int, timezoneOffset: Offset<String>, timezoneAbbreviationOffset: Offset<String>, timeframesOffset: VectorOffset<com.openmeteo.flatbuffers.forecast.Timeframe>) : Offset<Response> {
            builder.startTable(15)
            addLocationId(builder, locationId)
            addTimeframes(builder, timeframesOffset)
            addTimezoneAbbreviation(builder, timezoneAbbreviationOffset)
            addTimezone(builder, timezoneOffset)
            addUtcOffsetSeconds(builder, utcOffsetSeconds)
            addGenerationTimeMilliseconds(builder, generationTimeMilliseconds)
            addElevation(builder, elevation)
            addLongitude(builder, longitude)
            addLatitude(builder, latitude)
            addModel(builder, model)
            return endResponse(builder)
        }
        fun startResponse(builder: FlatBufferBuilder) = builder.startTable(15)

        fun addLatitude(builder: FlatBufferBuilder, latitude: Float) = builder.add(0, latitude, 0.0f)

        fun addLongitude(builder: FlatBufferBuilder, longitude: Float) = builder.add(1, longitude, 0.0f)

        fun addElevation(builder: FlatBufferBuilder, elevation: Float) = builder.add(2, elevation, 0.0f)

        fun addGenerationTimeMilliseconds(builder: FlatBufferBuilder, generationTimeMilliseconds: Float) = builder.add(3, generationTimeMilliseconds, 0.0f)

        fun addLocationId(builder: FlatBufferBuilder, locationId: Long) = builder.add(4, locationId, 0L)

        fun addModel(builder: FlatBufferBuilder, model: com.openmeteo.flatbuffers.forecast.Model) = builder.add(5, model.value, 0u)

        fun addUtcOffsetSeconds(builder: FlatBufferBuilder, utcOffsetSeconds: Int) = builder.add(6, utcOffsetSeconds, 0)

        fun addTimezone(builder: FlatBufferBuilder, timezone: Offset<String>) = builder.add(7, timezone, 0)

        fun addTimezoneAbbreviation(builder: FlatBufferBuilder, timezoneAbbreviation: Offset<String>) = builder.add(8, timezoneAbbreviation, 0)

        fun addTimeframes(builder: FlatBufferBuilder, timeframes: VectorOffset<com.openmeteo.flatbuffers.forecast.Timeframe>) = builder.add(14, timeframes, 0)

        fun createTimeframesVector(builder: FlatBufferBuilder, vector:com.openmeteo.flatbuffers.forecast.TimeframeOffsetArray) : VectorOffset<com.openmeteo.flatbuffers.forecast.Timeframe> {
            builder.startVector(4, vector.size, 4)
            for (i in vector.size - 1 downTo 0) {
                builder.add(vector[i])
            }
            return builder.endVector()
        }

        fun startTimeframesVector(builder: FlatBufferBuilder, numElems: Int) = builder.startVector(4, numElems, 4)

        fun endResponse(builder: FlatBufferBuilder) : Offset<Response> {
            val o: Offset<Response> = builder.endTable()
            return o
        }

        fun finishResponseBuffer(builder: FlatBufferBuilder, offset: Offset<Response>) = builder.finish(offset)

        fun finishSizePrefixedResponseBuffer(builder: FlatBufferBuilder, offset: Offset<Response>) = builder.finishSizePrefixed(offset)
    }
}

typealias ResponseOffsetArray = OffsetArray<Response>

inline fun ResponseOffsetArray(size: Int, crossinline call: (Int) -> Offset<Response>): ResponseOffsetArray =
    ResponseOffsetArray(IntArray(size) { call(it).value })