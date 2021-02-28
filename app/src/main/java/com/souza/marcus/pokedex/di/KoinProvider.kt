package com.souza.marcus.pokedex.di

import androidx.multidex.MultiDexApplication
import com.souza.marcus.pokedex.features.home.di.HomeModule
import com.souza.marcus.pokedex.remotedata.di.RemoteDataModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level
import org.koin.dsl.KoinAppDeclaration

internal object KoinProvider {
    fun get(application: MultiDexApplication): KoinAppDeclaration = {
        androidLogger(Level.DEBUG)
        androidContext(application)

        RemoteDataModule.load()
        HomeModule.load()
    }
}