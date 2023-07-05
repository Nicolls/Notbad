package com.notbad.thirdpart

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.notbad.lib.common.LogUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Call
import okhttp3.Callback
import okhttp3.FormBody
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

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

    private val client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                LogUtils.d(TAG, "app intercept ")
                val request = chain.request()
                val response = chain.proceed(request)
                return response
            }

        }).addNetworkInterceptor(object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                LogUtils.d(TAG, "network intercept ")
                val request = chain.request()
                val response = chain.proceed(request)
                return response
            }


        })
        .build()

    fun onTest(view: View) {
        LogUtils.d(TAG, "onTest")
        getRequest()
    }

    private fun getRequest() {
        lifecycleScope.launch(Dispatchers.IO) {
            val getRequest = Request.Builder().url(GET_URL).get().build()
            LogUtils.d(TAG, "start request ${Thread.currentThread().name}")
            client.newCall(getRequest).enqueue(object :Callback{
                override fun onFailure(call: Call, e: IOException) {
                    LogUtils.d(TAG, "error")
                }

                override fun onResponse(call: Call, response: Response) {
                    LogUtils.d(TAG, "onResponse ${Thread.currentThread().name}")
                    val getResult = response.body()?.string()
                    LogUtils.d(TAG, "get:$getResult")
                }

            })
            LogUtils.d(TAG, "end request ${Thread.currentThread().name}")
        }
    }

    private fun postRequest() {
        lifecycleScope.launch(Dispatchers.IO) {

            val reqBody = FormBody.Builder()
                .add("mobile", "18289454846")
                .add("ver_code", "123456").build()
            val postReq = Request.Builder().post(reqBody).url(POST_URL).build()
            val postResp = client.newCall(postReq).execute()
            val postResult = postResp.body()?.string()
            LogUtils.d(TAG, "post result:$postResult")
        }
    }
}