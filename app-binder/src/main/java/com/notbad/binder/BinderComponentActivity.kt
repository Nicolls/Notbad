package com.notbad.binder

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.os.Message
import android.os.Messenger
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.notbad.binder.LocalBinderService.MyBinder
import com.notbad.lib.common.LogUtils
import com.notbad.lib.common.MainThreadUtils

/**
 * Binder
 */
class BinderComponentActivity : AppCompatActivity() {
    private val TAG = "BinderComponentActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LogUtils.d(TAG, "onCreate")
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)
        findViewById<TextView>(R.id.btn1).setOnClickListener(this::onTest)
        findViewById<TextView>(R.id.btn2).setOnClickListener(this::onTest2)
    }



    fun onTest(view:View) {
        LogUtils.d(TAG, "onTest")

    }

    fun onTest2(view:View) {
        LogUtils.d(TAG, "onTest2")

    }

    private lateinit var  messenger:Messenger
    private val remoteConnect = object : ServiceConnection{
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            LogUtils.d(TAG, "onServiceConnected")
            messenger = Messenger(service)
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            LogUtils.d(TAG, "onServiceDisconnected")
        }

    }

    private fun bindMessenger(){
        val intent = Intent()
        intent.setComponent(ComponentName("com.notbad.binder","com.notbad.binder.RemoteMessengerBinderService"))
        startService(intent)
        MainThreadUtils.post(Runnable {
            LogUtils.d(TAG, "start to bind")
            bindService(intent,remoteConnect, Context.BIND_AUTO_CREATE)
        },2000L)
    }



    private val localConnect = object :ServiceConnection{
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            LogUtils.d(TAG, "onServiceConnected " + service)

            val helloBinder = service as MyBinder
            val result = helloBinder.service.calculate(12,3)
            LogUtils.d(TAG, "result is $result")
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            LogUtils.d(TAG, "onServiceDisconnected " + name)
        }

    }

    private fun startLocal (){
        val intent = Intent()
        intent.setComponent(ComponentName("com.notbad.binder","com.notbad.binder.LocalBinderService"))
        startService(intent)
        MainThreadUtils.post(Runnable {
            LogUtils.d(TAG, "start to bind")
            bindService(intent,localConnect, Context.BIND_AUTO_CREATE)
        },2000L)
    }

    private fun stopLocal(){
        val intent = Intent()
        intent.setComponent(ComponentName("com.notbad.binder","com.notbad.binder.LocalBinderService"))
        unbindService(localConnect)
    }

}