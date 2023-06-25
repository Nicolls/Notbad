package com.notbad.hotfix.plugin

import android.content.Intent
import android.content.res.Resources
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.notbad.lib.common.LogUtils
import java.lang.Exception

private const val TAG = "PluginSecondActivity"

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
        LogUtils.d(TAG, "这是插件二级页的代码，app是：$application - ${application::javaClass.name} - ${applicationContext}")
        setContentView(R.layout.activity_second)
        window.decorView.setBackgroundColor(Color.BLUE)
    }

    fun onTest(view: View) {
        LogUtils.d(TAG, "onTest ")
        startActivity(Intent(this, MainActivity::class.java))
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        LogUtils.d(TAG, "onNewIntent ${hashCode()}")
    }

    override fun onDestroy() {
        super.onDestroy()
        LogUtils.d(TAG, "onDestroy ${hashCode()}")
    }

    override fun getResources(): Resources {
        if(application!=null) {
            LogUtils.d(TAG, "start to get getResources")
            try {
                val resMethod = application::class.java.getDeclaredMethod("getPluginResources", String::class.java)
                resMethod.isAccessible = true
                val res= resMethod.invoke(application,"com.notbad.mmmmmm") as Resources
                return res
            }catch (e:Exception) {
                LogUtils.e(TAG, "getResule error",e)
            }
        }
        return super.getResources()
    }
}