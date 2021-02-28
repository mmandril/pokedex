package com.souza.marcus.pokedex.remotedata.provider

interface HttpClient {
    fun <T> create(service: Class<T>): T
}