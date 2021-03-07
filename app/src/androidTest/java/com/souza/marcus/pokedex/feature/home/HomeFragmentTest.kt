package com.souza.marcus.pokedex.feature.home

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.souza.marcus.pokedex.feature.home.robot.HomeRobot
import com.souza.marcus.pokedex.feature.home.robot.MockRobot
import com.souza.marcus.pokedex.features.home.presentation.fragment.HomeFragment
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
internal class HomeFragmentTest {

    private fun robots(func: HomeRobot.() -> Unit) = HomeRobot().apply(func)
    private fun mocks(func: MockRobot.() -> Unit) = MockRobot().apply(func)

    @Before
    fun setup() {
        launchFragmentInContainer<HomeFragment>()
    }

    @Test
    fun whenResultIsOk_shouldDisplayRecyclerViewVisible() {
        mocks {
            successResponseWith3Itens()
        }

        robots {
            checkIfRecyclerViewIsVisible()
        }
    }
}