package com.notbad.lib.common

import android.os.Handler
import android.os.Looper

object MainThreadUtils {
    @JvmStatic
    fun post(task: Runnable, delay: Long = 0) {
        val handler = Handler(Looper.getMainLooper())
        if (delay <= 0) {
            handler.post(task)
        } else {
            handler.postDelayed(task, delay)
        }
    }
}