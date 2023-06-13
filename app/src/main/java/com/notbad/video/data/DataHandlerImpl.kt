package com.notbad.video.data

import android.util.Log
import javax.inject.Inject

private const val TAG = "DataHandlerImpl"
class DataHandlerImpl @Inject constructor():IDataHandler {
    override fun handleData(data: String) {
        Log.d(TAG, "i handle data $data")
    }
}