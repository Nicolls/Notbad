package com.notbad.thirdpart

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.notbad.lib.common.LogUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request

/**
 * 三方库
 */
class ThirdPartActivity : AppCompatActivity() {
    private val TAG = "ThirdPartActivity"
    private val GET_URL =
        "https://raw.githubusercontent.com/Nicolls/sources/main/samples/okhttp/hello.json"
    private val POST_URL = "https://demo-api.apipost.cn/api/demo/login"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LogUtils.d(TAG, "onCreate")
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)

    }

    private val client: OkHttpClient = OkHttpClient()
    fun onTest(view: View) {
        LogUtils.d(TAG, "onTest")
        handlerTest()
    }

    private fun handlerTest(){
        Handler().sendEmptyMessage(0)
    }

    private fun getRequest() {
        lifecycleScope.launch(Dispatchers.IO) {
            val getRequest = Request.Builder().url(GET_URL).get().build()
            val getResp = client.newCall(getRequest).execute()
            val getResult = getResp.body?.string()
            LogUtils.d(TAG, "get:$getResult")
        }
    }

    private fun postRequest() {
        lifecycleScope.launch(Dispatchers.IO) {

            val reqBody = FormBody.Builder()
                .add("mobile", "18289454846")
                .add("ver_code", "123456").build()
            val postReq = Request.Builder().post(reqBody).url(POST_URL).build()
            val postResp = client.newCall(postReq).execute()
            val postResult = postResp.body?.string()
            LogUtils.d(TAG, "post result:$postResult")
        }
    }
}