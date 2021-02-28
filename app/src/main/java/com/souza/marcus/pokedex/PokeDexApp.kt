package com.souza.marcus.pokedex

import androidx.multidex.MultiDexApplication
import com.souza.marcus.pokedex.di.KoinProvider
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin

@KoinApiExtension
internal class PokeDexApp : MultiDexApplication(), KoinComponent {

    override fun onCreate() {
        super.onCreate()
        startKoinDependencies()
    }

    private fun startKoinDependencies() {
        startKoin(
            appDeclaration = KoinProvider.get(this)
        )
    }
}