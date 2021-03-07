package com.souza.marcus.pokedex.feature.home

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.souza.marcus.pokedex.R
import com.souza.marcus.pokedex.feature.home.helper.successJson
import com.souza.marcus.pokedex.feature.home.robot.HomeRobot
import com.souza.marcus.pokedex.features.home.presentation.fragment.HomeFragment
import com.souza.marcus.pokedex.util.BaseTest
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
internal class HomeFragmentTest : BaseTest() {

    private fun robots(func: HomeRobot.() -> Unit) = HomeRobot().apply(func)

    @Before
    fun setup() {
        super.server()
        launchFragmentInContainer<HomeFragment>(themeResId = R.style.Theme_AppCompat)
    }

    @Test
    fun whenResultIsOk_shouldDisplayRecyclerViewVisible() {
        successResponseWith3Itens()

        robots {
            checkIfRecyclerViewIsVisible()
        }
    }

    private fun successResponseWith3Itens() {
        webServer.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return MockResponse().setResponseCode(200).setBody(successJson)
            }
        }
    }
}