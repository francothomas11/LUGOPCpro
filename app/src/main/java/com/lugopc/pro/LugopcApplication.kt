package com.lugopc.pro

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class LugopcApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Initialize app-level configurations
    }
}
