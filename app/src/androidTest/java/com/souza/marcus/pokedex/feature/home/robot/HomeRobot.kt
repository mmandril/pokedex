package com.souza.marcus.pokedex.feature.home.robot

import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import com.souza.marcus.pokedex.R

internal class HomeRobot {

    fun checkIfRecyclerViewIsVisible() {
        Espresso.onView(ViewMatchers.withId(R.id.homeRecyclerView))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}