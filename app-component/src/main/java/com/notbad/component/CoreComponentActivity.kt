package com.notbad.component

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
import com.notbad.binder.IHelloService
import com.notbad.binder.IRemoteCallBack
import com.notbad.lib.common.LogUtils
import com.notbad.lib.common.MainThreadUtils
import com.notbad.binder.IRemoteService
import com.notbad.binder.Student
import java.security.Provider.Service

/**
 * android核心组件
 */
class CoreComponentActivity : AppCompatActivity() {
    private val TAG = "CoreComponentActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LogUtils.d(TAG, "onCreate")
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)
        findViewById<TextView>(R.id.btn1).setOnClickListener(this::onTest)
        findViewById<TextView>(R.id.btn2).setOnClickListener(this::onTest2)
    }

    private var  helloService:IHelloService?=null

    private val customConnect = object :ServiceConnection{
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            LogUtils.d(TAG, "onServiceConnected ")
            helloService = IHelloService.Stub.asInterface(service)
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            LogUtils.d(TAG, "onServiceDisconnected")
            helloService = null
        }

    }


    fun onTest(view:View) {
        LogUtils.d(TAG, "onTest")
//        val intent = Intent()
//        intent.setComponent(ComponentName("com.notbad.binder","com.notbad.binder.RemoteCustomHelloService"))
//        LogUtils.d(TAG, "start to bind")
//        bindService(intent,customConnect, Context.BIND_AUTO_CREATE)
        aidlServiceConnect();
    }

    fun onTest2(view:View) {
        LogUtils.d(TAG, "onTest2")
//        val result = helloService?.printMsg("hello")
//        LogUtils.d(TAG, "after result:$result")
        aidlServiceUpdateData();
    }

    private fun aidlServiceConnect(){
        val intent = Intent()
        intent.setComponent(ComponentName("com.notbad.binder","com.notbad.binder.RemoteAidlBinderService"))
        LogUtils.d(TAG, "start to bind")
        bindService(intent,aidlRemoteConnect, Context.BIND_AUTO_CREATE)
    }

    private fun aidlServiceUpdateData(){
        val student = Student("mjk",33)
        LogUtils.d(TAG, "before:$student")
        myService.upgradeStudent(student)
        LogUtils.d(TAG, "after result:$student")
    }


    private lateinit var myService:IRemoteService;

    // 这里我们需要通过Stub来创建实例
    private val callBack:IRemoteCallBack = object : IRemoteCallBack.Stub() {
        override fun onNewStudent(student: Student?) {
            LogUtils.d(TAG, "${this.hashCode()} onNewStudent receive:$student")
        }

    }

    private val aidlRemoteConnect = object :ServiceConnection{
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            LogUtils.d(TAG, "onServiceConnected " + service)
            myService = IRemoteService.Stub.asInterface(service)
            // 我们这个callback到服务端那边后会创建新的callback来表示它。所以你会看到服务端的callback和hashcode和我们这里设置进去的是不一样的。
            LogUtils.d(TAG, "our callback:" + callBack.hashCode())
            myService.setCallBack(callBack)
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            LogUtils.d(TAG, "onServiceDisconnected " + name)
        }

    }



    private lateinit var  messenger: Messenger
    private val remoteConnect = object : ServiceConnection {
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
        LogUtils.d(TAG, "start to bind")
        bindService(intent,remoteConnect, Context.BIND_AUTO_CREATE)
    }

}