package com.notbad.hotfix.plugin

import android.content.Intent
import android.content.res.Resources
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.notbad.lib.common.LogUtils
import java.lang.Exception

/**
 * Hook ams 来实现的组件插件化
 */
private const val TAG = "MyPluginActivity"
class MyPluginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LogUtils.d(TAG, "onCreate ")
        setContentView(R.layout.activity_my_plugin)
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
            }catch (e: Exception) {
                LogUtils.e(TAG, "getResule error", e)
            }
        }
        return super.getResources()
    }
}