package com.notbad.hilt

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.notbad.feature.wallpaper.FooUsage
import com.notbad.lib.common.LogUtils
import com.notbad.hilt.R
import com.notbad.hilt.data.DataHandlerImpl
import com.notbad.hilt.data.IDataHandler
import com.notbad.hilt.data.VideoRepository
import com.notbad.hilt.network.RetrofitService
import com.notbad.hilt.view.MainFragment
import com.notbad.hilt.view.SubFragment
import com.notbad.hilt.viewmodel.VideoViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


private const val TAG = "MainActivity"


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var mainFragment: MainFragment
    lateinit var subFragment: SubFragment

    @Inject
    lateinit var dataHandler: IDataHandler

    @Inject
    lateinit var retrofitService: RetrofitService

    @Inject
    lateinit var retrofitService2: RetrofitService

    @Inject
    lateinit var repository: VideoRepository

    lateinit var videoViewModel: VideoViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (dataHandler as DataHandlerImpl).handleType = "Main Handle"
        LogUtils.d(TAG, "onCreate ${savedInstanceState} ${hashCode()}")
        mainFragment = MainFragment()
        subFragment = SubFragment()
        videoViewModel = ViewModelProvider(this)[VideoViewModel::class.java]
        videoViewModel.printCode()
    }

    fun onTest(view: View) {
        LogUtils.d(
            TAG,
            "onTest s1:${dataHandler.hashCode()} s2:${repository.dataHandler.hashCode()} "
        )
        repository.dataHandler.handleData("yes iam ")
        retrofitService.startRequest("my service")
        dataHandler.handleData("yes")
        val intent = Intent("com.notbad.search")
        startActivity(intent)
    }

    fun onReplace(view: View) {
        LogUtils.d(TAG, "onReplace")
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
        val replaceFragment = if (currentFragment is MainFragment) subFragment else mainFragment
        val fragmentTransition = supportFragmentManager.beginTransaction()
        fragmentTransition.replace(R.id.fragment_container, replaceFragment)
        fragmentTransition.commitNow()
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
        LogUtils.d(TAG, "onResume ${hashCode()}" )
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