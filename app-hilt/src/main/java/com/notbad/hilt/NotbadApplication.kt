package com.notbad.hilt

import android.app.Application
import android.util.Log
import com.notbad.lib.common.LogUtils
import dagger.hilt.android.HiltAndroidApp

private const val TAG = "NotBadApplication"

@HiltAndroidApp
class NotBadApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        LogUtils.d(TAG, "onCreate")
    }

}