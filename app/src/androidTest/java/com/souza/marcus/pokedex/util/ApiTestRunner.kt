package com.souza.marcus.pokedex.util

import android.app.Application
import androidx.test.runner.AndroidJUnitRunner

class ApiTestRunner : AndroidJUnitRunner() {

    override fun callApplicationOnCreate(app: Application?) {
        super.callApplicationOnCreate(app)
    }
}