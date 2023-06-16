package com.notbad.lib.common

import android.util.Log

object LogUtils {
    private const val PREF = "NotBad_"
    fun d(tag:String,msg:String) {
        Log.d("$PREF$tag",msg)
    }
    fun i(tag:String,msg:String) {
        Log.d("$PREF$tag",msg)
    }
    fun w(tag:String,msg:String) {
        Log.w("$PREF$tag",msg)
    }
    fun e(tag:String,msg:String) {
        Log.e("$PREF$tag",msg)
    }
}