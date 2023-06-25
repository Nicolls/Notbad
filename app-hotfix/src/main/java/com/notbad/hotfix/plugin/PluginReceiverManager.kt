package com.notbad.hotfix.plugin

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import com.notbad.lib.common.LogUtils
private const val TAG  = "PluginReceiverManager"
object PluginReceiverManager {
    fun register(context: Context){
        LogUtils.d(TAG, "在宿主中注册广播")
    }
}