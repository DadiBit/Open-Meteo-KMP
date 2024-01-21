package com.openmeteo.sdk.common

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.request
import io.ktor.http.Parameters
import io.ktor.http.URLBuilder
import io.ktor.http.Url
import io.ktor.http.parametersOf
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.properties.Properties
import kotlinx.serialization.properties.encodeToStringMap

/**
 * An Open-Meteo API endpoint that always returns [R] or a [List] of [R];
 * @param context The [Url] at which all requests should be made. Query is overwritten.
 * @param contentDecoder The [ContentDecoder] used to decode the body.
 */
open class Endpoint<R : Any>(
    var context: Url,
    val contentDecoder: ContentDecoder<R>
) {

    constructor(
        url: String,
        contentDecoder: ContentDecoder<R>,
    ) : this(Url(url), contentDecoder)

    private val client = HttpClient()

    /**
     * Perform a GET request to the context with the provided query parameters.
     * @param query The query to encode in the URL.
     */
    suspend fun get(query: Parameters = Parameters.Empty) =
        context.run {
            val url = URLBuilder(protocol, host, port, user, password, pathSegments,
                query, fragment, trailingQuery).build()
            client.get(url)
        }

    /**
     * Perform a GET request to the context with the provided [Query].
     * @param query The query to encode in the URL.
     */
    @OptIn(ExperimentalSerializationApi::class)
    suspend inline fun <reified Q : Query> get(query: @Serializable Q) =
        get(parametersOf(Properties.encodeToStringMap(query)))

    /**
     * Perform a GET request to the context with the provided query and decode
     * the response body, if successful.
     * @param query The query to encode in the URL.
     * @throws Error
     * @throws BadRequest
     */
    suspend inline operator fun <reified Q : Query> invoke(query: @Serializable Q) {
        // always default to Json, no matter what
        val format = (query as? Query.ContentFormat)
            ?.format ?: ContentFormat.Json
        // TODO: not optimal
        val asList = query is Query.Coordinates && query.latitude.size > 1
        val res = get(query)
        // handle success and errors
        when (res.status.value) {
            200 -> contentDecoder.decode(res, format, asList)
            400 -> throw ContentDecoder.decodeJson(BadRequest.serializer(), res)
            else -> throw Error("`${res.request.url}` response code: ${res.status}")
        }
    }

}

public inline fun parametersOf(map: Map<String, String>): Parameters =
    parametersOf(map.mapValues { listOf(it.value) })
