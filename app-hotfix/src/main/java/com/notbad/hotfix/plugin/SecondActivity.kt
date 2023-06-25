package com.notbad.hotfix.plugin

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.notbad.hotfix.R
import com.notbad.lib.common.LogUtils
private const val TAG = "SecondActivity"

/**
 * 插桩方式实现插件化，即将在宿主app中，先创建要插件app所需要的activity等组件，
 * 它们在宿主app中的实现都是空可以认为占坑，当我们把插件app加载进来后，就可以把宿主中对应的同名Activity都替换成我们插件中的，
 * 但这个要求所有占坑的组件Activity必须与插件同名（包名+类名），
 * intent注册也必须一样，这样才能在Dex加载的时候去优先命中插件app的类。一般可以用gradle来实现。
 */
class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LogUtils.d(TAG, "onCreate ")
        setContentView(R.layout.activity_second)
        window.decorView.setBackgroundColor(Color.WHITE)
    }


    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        LogUtils.d(com.notbad.hotfix.plugin.TAG, "onNewIntent ${hashCode()}")
    }

    override fun onDestroy() {
        super.onDestroy()
        LogUtils.d(com.notbad.hotfix.plugin.TAG, "onDestroy ${hashCode()}")
    }
}