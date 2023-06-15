package com.notbad.video

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.notbad.video.network.RetrofitService
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

private const val TAG = "SearchActivity"

@AndroidEntryPoint
class SearchActivity : AppCompatActivity() {
    @Inject
    lateinit var retrofitService: RetrofitService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")
        setContentView(R.layout.activity_search)
    }

    fun onTest(view: View) {
        Log.d(TAG, "onTest ${retrofitService.hashCode()}")
    }
}