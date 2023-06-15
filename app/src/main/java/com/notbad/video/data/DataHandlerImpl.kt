package com.notbad.video.data

import android.util.Log
import dagger.hilt.android.scopes.ActivityRetainedScoped
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject
import javax.inject.Singleton

private const val TAG = "DataHandlerImpl"
class DataHandlerImpl @Inject constructor():IDataHandler {
    var handleType: String? = null
    override fun handleData(data: String) {
        Log.d(TAG, "i handle data $data by $handleType")
    }
}