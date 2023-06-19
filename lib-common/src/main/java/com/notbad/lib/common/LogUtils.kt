package com.notbad.lib.common

import android.util.Log
import java.lang.Exception

object LogUtils {
    private const val PREF = "NotBad_"
    @JvmStatic
    fun d(tag:String,msg:String) {
        Log.d("$PREF$tag",msg)
    }
    @JvmStatic
    fun i(tag:String,msg:String) {
        Log.d("$PREF$tag",msg)
    }
    @JvmStatic
    fun w(tag:String,msg:String) {
        Log.w("$PREF$tag",msg)
    }
    @JvmStatic
    fun e(tag:String,msg:String,tr:Throwable?=null) {
        Log.e("$PREF$tag",msg,tr)
    }
}