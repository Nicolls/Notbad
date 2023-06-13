package com.notbad.video.data

import android.content.Context
import android.util.Log
import javax.inject.Inject

private const val TAG = "DataHandlerImpl2"
class DataHandlerImpl2 @Inject constructor(val context:Context):IDataHandler {
    override fun handleData(data: String) {
        Log.d(TAG, "you handle data $data orientation:${context.resources.configuration.orientation}")
    }
}