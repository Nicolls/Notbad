package com.notbad.video

import android.app.Application
import android.util.Log
import com.notbad.video.graph.DaggerNotBadAppGraph
import com.notbad.video.graph.NotBadAppGraph

private const val TAG = "NotBadApplication"
class NotBadApplication:Application() {

    lateinit var appGraph: NotBadAppGraph

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate")
        appGraph = DaggerNotBadAppGraph.factory().create(applicationContext)
    }

}