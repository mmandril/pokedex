package com.souza.marcus.pokedex.remotedata.di

import com.souza.marcus.pokedex.BuildConfig
import com.souza.marcus.pokedex.remotedata.interceptor.InterceptorProvider
import com.souza.marcus.pokedex.remotedata.provider.HttpClient
import com.souza.marcus.pokedex.remotedata.provider.impl.HttpClientImpl
import com.souza.marcus.pokedex.remotedata.retrofitclient.RetrofitClient
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

object RemoteDataModule {
    private val module = module {
        factory { InterceptorProvider.provider() }
        single {
            RetrofitClient.newInstance(
                baseUrl = BuildConfig.BASE_URL,
                okHttpClient = get()
            )
        }
        single<HttpClient> { HttpClientImpl(get()) }
        single<Moshi> {
            Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        }
    }

    fun load() {
        loadKoinModules(module)
    }
}