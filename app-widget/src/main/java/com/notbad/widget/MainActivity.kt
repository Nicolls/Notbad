package com.notbad.widget

import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import com.notbad.lib.common.LogUtils


class MainActivity : AppCompatActivity() {
    val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        LogUtils.d(TAG, "dispatchTouchEvent ${MotionEvent.actionToString(ev!!.action)}")
        if(ev.action==MotionEvent.ACTION_DOWN) {
            LogUtils.d(TAG, "down")
        } else if(ev.action==MotionEvent.ACTION_MOVE) {
            LogUtils.d(TAG, "move")
        } else if(ev.action==MotionEvent.ACTION_UP) {
            LogUtils.d(TAG, "up")
        }
        val result =  super.dispatchTouchEvent(ev)
//        val result =  false
        return result
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        LogUtils.d(TAG, "onTouchEvent ${MotionEvent.actionToString(event!!.action)}")
        if(event.action==MotionEvent.ACTION_DOWN) {
            LogUtils.d(TAG, "down")
        } else if(event.action==MotionEvent.ACTION_MOVE) {
            LogUtils.d(TAG, "move")
        } else if(event.action==MotionEvent.ACTION_UP) {
            LogUtils.d(TAG, "up")
        }
        val result =  super.onTouchEvent(event)
//        val result =  false
        return result
    }
}