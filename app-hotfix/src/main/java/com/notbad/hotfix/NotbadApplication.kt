package com.notbad.hotfix

import android.app.Application
import android.util.Log
import com.notbad.lib.common.LogUtils

private const val TAG = "NotBadApplication"

class NotBadApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        LogUtils.d(TAG, "onCreate")
        HotFixManager.loadDex(this) // 每次onCreate的时候都要把我们的新dex加载一下，放到前面
    }

}