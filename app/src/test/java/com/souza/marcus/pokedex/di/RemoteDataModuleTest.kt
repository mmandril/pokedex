package com.souza.marcus.pokedex.di

import com.souza.marcus.pokedex.remotedata.di.RemoteDataModule
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.koinApplication
import org.koin.test.check.checkModules

internal class RemoteDataModuleTest {

    @Before
    fun setup() {
        startKoin { }
    }

    @Test
    fun `load should provide dependencies on Koin of RemoteDataModule with success`() {
        koinApplication {
            RemoteDataModule.load()
        }.checkModules()
    }

    @After
    fun after() {
        stopKoin()
    }
}