package com.notbad.video.network

import android.content.Context
import android.util.Log

private const val TAG = "RetrofitService"
class RetrofitService(private val context:Context) {

    fun startRequest(from:String){
        Log.d(TAG, "startRequest $from $context")
    }
}