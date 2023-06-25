package com.notbad.hotfix

import android.app.Application
import android.content.res.Resources
import android.util.Log
import com.notbad.lib.common.LogUtils

private const val TAG = "NotBadApplication"

class NotBadApplication : Application() {
    private var resources: Resources?=null
    override fun onCreate() {
        super.onCreate()
        LogUtils.d(TAG, "onCreate")
        HotFixManager.loadDex(this) // 每次onCreate的时候都要把我们的新dex加载一下，放到前面
        resources= HotFixManager.hookResources(this)
        LogUtils.d(TAG, "app create res:$resources")
    }

    fun getPluginResources(packageName:String): Resources {
        LogUtils.d(TAG, "getPluginResources pack:$packageName")
        return resources?:getResources()
    }


}