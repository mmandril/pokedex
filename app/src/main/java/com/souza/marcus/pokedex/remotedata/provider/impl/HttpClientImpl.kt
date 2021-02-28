package com.souza.marcus.pokedex.remotedata.provider.impl

import com.souza.marcus.pokedex.remotedata.provider.HttpClient
import retrofit2.Retrofit

internal class HttpClientImpl(
    private val retrofit: Retrofit
) : HttpClient {
    override fun <T> create(service: Class<T>): T {
        return retrofit.create(service)
    }
}