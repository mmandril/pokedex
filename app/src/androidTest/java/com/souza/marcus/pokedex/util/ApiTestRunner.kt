package com.souza.marcus.pokedex.util

import android.app.Application
import androidx.test.runner.AndroidJUnitRunner
import com.souza.marcus.pokedex.features.home.di.HomeModule
import com.souza.marcus.pokedex.remotedata.di.RemoteDataModule
import org.koin.core.context.startKoin

class ApiTestRunner : AndroidJUnitRunner() {

    override fun callApplicationOnCreate(app: Application?) {
        super.callApplicationOnCreate(app)
        startKoin {
            RemoteDataModule.load()
            HomeModule.load()
        }
    }
}