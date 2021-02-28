package com.souza.marcus.pokedex.remotedata.interceptor

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

internal object InterceptorProvider {

    fun provider(): OkHttpClient {
        return OkHttpClient.Builder().apply {
            HttpLoggingInterceptor().also { httpLoggingInterceptor ->
                httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
                addInterceptor(httpLoggingInterceptor)
            }
        }.build()
    }
}