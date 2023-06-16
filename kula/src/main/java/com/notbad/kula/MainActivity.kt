package com.notbad.kula

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import com.notbad.lib.common.LogUtils

private const val TAG = "KulaMain"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LogUtils.d(TAG, "onCreate")
        setContentView(R.layout.activity_main)
    }

    fun onTest(view: View) {
        LogUtils.d(TAG, "onTest")
        val intent  = Intent(this,SecondActivity::class.java)
        startActivity(intent)
    }

    fun onTest2(view: View) {
        LogUtils.d(TAG, "onTest2")
        val intent = Intent("com.notbad.hula.seconds")
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        LogUtils.d(TAG, "onNewIntent ${hashCode()}")
    }

    override fun onRestart() {
        super.onRestart()
        LogUtils.d(TAG, "onRestart ${hashCode()}")
    }

    override fun onStart() {
        super.onStart()
        LogUtils.d(TAG, "onStart ${hashCode()}")
    }

    override fun onResume() {
        super.onResume()
        LogUtils.d(TAG, "onResume ${hashCode()}")
    }

    override fun onPause() {
        super.onPause()
        LogUtils.d(TAG, "onPause ${hashCode()}")
    }

    override fun onStop() {
        super.onStop()
        LogUtils.d(TAG, "onStop ${hashCode()}")
    }

    override fun onDestroy() {
        super.onDestroy()
        LogUtils.d(TAG, "onDestroy ${hashCode()}")
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        LogUtils.d(TAG, "onConfigurationChanged ${hashCode()} $newConfig")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        LogUtils.d(TAG, "onSaveInstanceState ${hashCode()} $outState")
    }

    override fun onRestoreInstanceState(
        savedInstanceState: Bundle?,
        persistentState: PersistableBundle?
    ) {
        super.onRestoreInstanceState(savedInstanceState, persistentState)
        LogUtils.d(TAG, "onRestoreInstanceState ${hashCode()} $savedInstanceState")
    }
}

