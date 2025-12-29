package com.cubey.keyboard

import android.app.Application
import android.content.res.Configuration
import java.util.Locale

class CubeyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        setEnglishLocale()
    }

    private fun setEnglishLocale() {
        val config = Configuration(resources.configuration)
        config.setLocale(Locale.ENGLISH)
        @Suppress("DEPRECATION")
        resources.updateConfiguration(config, resources.displayMetrics)
    }
}
