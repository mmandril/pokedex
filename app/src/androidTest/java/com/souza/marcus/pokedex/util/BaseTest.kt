package com.souza.marcus.pokedex.util

import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.koin.test.KoinTest

private const val MOCK_WEB_SERVER_PORT = 4007

open class BaseTest : KoinTest {

    protected lateinit var webServer: MockWebServer

    @Before
    fun server() {
        webServer = MockWebServer()
        webServer.start(MOCK_WEB_SERVER_PORT)
    }

    @After
    fun tearDown() {
        webServer.shutdown()
    }
}