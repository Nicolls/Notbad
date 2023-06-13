package com.notbad.video

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.notbad.video.graph.LoginViewModel
import com.notbad.video.viewmodel.VideoViewModel
import javax.inject.Inject

private const val TAG = "SearchActivity"
class SearchActivity : AppCompatActivity() {
    @Inject
    lateinit var videoViewModel: VideoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as NotBadApplication).appGraph.inject(this)
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")
        setContentView(R.layout.activity_search)
    }

    fun onTest(view: View) {
        Log.d(TAG, "onTest")
        Log.d(TAG, "video:${videoViewModel.hashCode()} - ${videoViewModel.name}")
        videoViewModel.repository.remoteDataSource.dataHandler.handleData("yues")
    }
}