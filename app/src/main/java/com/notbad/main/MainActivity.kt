package com.notbad.main

import android.content.ComponentName
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.ServiceConnection
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.IBinder
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.notbad.binder.IRemoteService
import com.notbad.binder.Student
import com.notbad.lib.common.LogUtils

private const val TAG = "App-MainActivity"
private const val URL = "http://www.kkkk.htpljsfiw.hph"
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        LogUtils.d(TAG, "$URL")
        val m = "www.baidu.com"
        LogUtils.d(TAG, "$m")

    }
    private lateinit var myService: IRemoteService;

    private val aidlRemoteConnect = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            LogUtils.d(TAG, "onServiceConnected " + service)
            myService = IRemoteService.Stub.asInterface(service)
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            LogUtils.d(TAG, "onServiceDisconnected " + name)
        }

    }

    fun onTest(view: View) {
        val intent = Intent()
        intent.setComponent(ComponentName("com.notbad.binder","com.notbad.binder.RemoteAidlBinderService"))
        LogUtils.d(TAG, "start to bind")
        bindService(intent,aidlRemoteConnect, Context.BIND_AUTO_CREATE)

    }

    fun onTest2(view: View) {
        val student = Student("Kjm",32)
        LogUtils.d(TAG, "insert before:$student")
        myService.insertStudent(student)
    }
}