package com.notbad.main

import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
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

    fun onTest(view: View) {

        val dis = resources.displayMetrics
        LogUtils.d(TAG, "density:${dis.density} ${dis.densityDpi}  ${dis.scaledDensity} ${dis.xdpi} ${dis.ydpi} ${dis.toString()}")
        AlertDialog.Builder(this).setTitle("Hello")
            .setPositiveButton("同意", DialogInterface.OnClickListener { dialog, which ->  }).setMessage("是我")
            .create().show()

    }
}