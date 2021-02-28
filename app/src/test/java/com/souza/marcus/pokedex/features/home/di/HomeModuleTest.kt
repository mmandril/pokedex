package com.souza.marcus.pokedex.features.home.di

import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.dsl.koinApplication
import org.koin.test.check.checkModules

internal class HomeModuleTest {

    @Before
    fun setup() {
        startKoin { }
    }

    @Test
    fun `load should provide dependencies on Koin of HomeModule with success`() {
        koinApplication {
            HomeModule.load()
        }.checkModules()
    }
}