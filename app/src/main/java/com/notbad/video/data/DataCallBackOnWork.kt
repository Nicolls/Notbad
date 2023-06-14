package com.notbad.video.data

import android.util.Log
import javax.inject.Inject

private const val TAG = "DataCallBackOnWork"
class DataCallBackOnWork @Inject constructor():IDataCallBack {
    override fun onCall(msg: String) {
        Log.d(TAG, "onCall work $msg")
    }
}