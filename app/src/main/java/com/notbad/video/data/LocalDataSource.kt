package com.notbad.video.data

import android.util.Log
import javax.inject.Inject

private const val TAG = "LocalDataSource"
class LocalDataSource @Inject constructor(val dataHandler:IDataHandler) {
    fun printName(data:String){
        dataHandler.handleData(data)
    }
}