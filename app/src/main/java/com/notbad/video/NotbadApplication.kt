package com.notbad.video

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp

private const val TAG = "NotBadApplication"

@HiltAndroidApp
class NotBadApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate")
    }

}