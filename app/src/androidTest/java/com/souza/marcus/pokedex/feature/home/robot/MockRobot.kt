package com.souza.marcus.pokedex.feature.home.robot

import com.souza.marcus.pokedex.feature.home.helper.successJson
import com.souza.marcus.pokedex.util.BaseTest
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

internal class MockRobot : BaseTest() {

    fun successResponseWith3Itens() {
        webServer.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return MockResponse().setResponseCode(200).setBody(successJson)
            }
        }
    }
}