package com.notbad.video

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity

private const val TAG = "SearchActivity"

class SearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")
        setContentView(R.layout.activity_search)
    }

    fun onTest(view: View) {
        Log.d(TAG, "onTest")
    }
}