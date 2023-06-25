package com.notbad.hotfix

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.notbad.hotfix.handler.Calculate
import com.notbad.lib.common.LogUtils
import java.lang.Exception


private const val TAG = "MainActivity"


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    fun onTest(view: View) {
        LogUtils.d(TAG, "onTest ")
        HotFixManager.reCreate(this)
        val intent = Intent("com.notbad.hotfix.plugin.second")
        try {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }catch (e:Exception){
            e.printStackTrace()
        }
    }


    private fun testDex() {
        LogUtils.d(TAG, "testDex ")
        val a = 10
        val b = 5
        val op = "x"
        val result = Calculate.cal(a, b, op)
        val str = "$a $op $b = $result"
        Toast.makeText(this, "$str", Toast.LENGTH_SHORT).show()
    }


    fun onLoad(view: View) {
        LogUtils.d(TAG, "onLoad")
        HotFixManager.loadDex(this)
        HotFixManager.reCreate(this)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        LogUtils.d(TAG, "onNewIntent ${hashCode()}")
    }

    override fun onDestroy() {
        super.onDestroy()
        LogUtils.d(TAG, "onDestroy ${hashCode()}")
    }
}