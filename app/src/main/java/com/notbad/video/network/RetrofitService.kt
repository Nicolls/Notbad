package com.notbad.video.network

import android.content.Context
import android.util.Log
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject
import javax.inject.Singleton

private const val TAG = "RetrofitService"

class RetrofitService @Inject constructor() {

    fun startRequest(from:String){
//        Log.d(TAG, "startRequest $from $context")
    }
}