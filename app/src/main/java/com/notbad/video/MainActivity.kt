package com.notbad.video

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.notbad.feature.wallpaper.FooUsage
import com.notbad.video.data.DataHandlerImpl
import com.notbad.video.data.IDataCallBack
import com.notbad.video.data.IDataHandler
import com.notbad.video.data.VideoRepository
import com.notbad.video.network.RetrofitService
import com.notbad.video.view.MainFragment
import com.notbad.video.view.SubFragment
import com.notbad.video.viewmodel.VideoViewModel
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
    lateinit var dataCallBack: IDataCallBack

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
        Log.d(TAG, "onCreate ${dataHandler.hashCode()}")
        mainFragment = MainFragment()
        subFragment = SubFragment()
        videoViewModel = ViewModelProvider(this)[VideoViewModel::class.java]
        videoViewModel.printCode()
    }

    fun onTest(view: View) {
        Log.d(
            TAG,
            "onTest s1:${dataHandler.hashCode()} s2:${repository.localDataSource.dataHandler.hashCode()} "
        )
        repository.remoteDataSource.dataHandler.handleData("yes iam ")
        retrofitService.startRequest("my service")
        dataCallBack.onCall("hello")
        dataHandler.handleData("yes")
        FooUsage.fooSay(this)
    }

    fun onReplace(view: View) {
        Log.d(TAG, "onReplace")
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
        val replaceFragment = if (currentFragment is MainFragment) subFragment else mainFragment
        val fragmentTransition = supportFragmentManager.beginTransaction()
        fragmentTransition.replace(R.id.fragment_container, replaceFragment)
        fragmentTransition.commitNow()
    }
}