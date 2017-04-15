package net.cydhra.nidhogg

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.sun.jersey.api.client.Client
import com.sun.jersey.api.client.ClientResponse
import javax.ws.rs.core.MediaType

internal const val DEFAULT_CLIENT_TOKEN = "Nidhogg"

abstract class NidhoggClient(private val userAgent: String) {

    protected val gson: Gson

    init {
        this.gson = GsonBuilder().create()
    }

    protected fun executeRequest(host: String, endpoint: String, body: String): ClientResponse {
        assert(endpoint.startsWith("/"))
        val resource = Client.create().resource(host).path(endpoint)
        return resource
                .header("User-Agent", DEFAULT_CLIENT_TOKEN)
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .entity(body, MediaType.APPLICATION_JSON_TYPE)
                .post<ClientResponse>(ClientResponse::class.java)
    }
}