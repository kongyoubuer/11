package com.modernapp.gallery

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyModernApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}
