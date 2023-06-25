package com.notbad.hotfix.plugin

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import com.notbad.lib.common.LogUtils
private const val TAG = "PluginReceiverManager"
object PluginReceiverManager {
    private val pluginReceiver:PluginReceiver = PluginReceiver()
    class PluginReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            LogUtils.d(TAG, "我收到了广播，我现在在插件app中")
        }
    }


    fun register(context: Context){
        LogUtils.d(TAG, "在插件app中注册广播")
        val filter = IntentFilter()
        filter.addAction("com.notbad.hotfix.plugin.receiver")
        context.registerReceiver(pluginReceiver,filter)
    }
}