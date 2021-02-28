package com.souza.marcus.pokedex.remotedata.di

import com.souza.marcus.pokedex.BuildConfig
import com.souza.marcus.pokedex.remotedata.provider.HttpClient
import com.souza.marcus.pokedex.remotedata.provider.impl.HttpClientImpl
import com.souza.marcus.pokedex.remotedata.retrofitclient.RetrofitClient
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

object RemoteDataModule {
    private val module = module {
        single {
            RetrofitClient.newInstance(
                baseUrl = BuildConfig.BASE_URL,
                okHttpClient = get()
            )
        }
        single<HttpClient> { HttpClientImpl(get()) }
    }

    fun load() {
        loadKoinModules(module)
    }
}