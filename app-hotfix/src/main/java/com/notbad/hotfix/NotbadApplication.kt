package com.notbad.hotfix

import android.app.Application
import android.util.Log
import com.notbad.lib.common.LogUtils

private const val TAG = "NotBadApplication"

class NotBadApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        LogUtils.d(TAG, "onCreate")
    }

}