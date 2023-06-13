package com.notbad.video

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainer
import androidx.fragment.app.FragmentContainerView
import com.notbad.video.graph.LoginGraph
import com.notbad.video.graph.LoginViewModel
import com.notbad.video.view.MainFragment
import com.notbad.video.view.SubFragment
import com.notbad.video.viewmodel.VideoViewModel
import javax.inject.Inject

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    lateinit var loginGraph: LoginGraph
    @Inject
    lateinit var videoViewModel: VideoViewModel

    lateinit var fragmentContainer: FragmentContainerView

    lateinit var mainFragment:MainFragment
    lateinit var subFragment:SubFragment

    override fun onCreate(savedInstanceState: Bundle?) {
//        (application as NotBadApplication).appGraph.inject(this)
        loginGraph = (application as NotBadApplication).appGraph.registerLoginGraph().create()
        loginGraph.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fragmentContainer = findViewById(R.id.fragment_container)
        mainFragment = MainFragment()
        subFragment = SubFragment()
    }

    fun onTest(view: View) {
        Log.d(TAG, "onTest")
        videoViewModel.repository.remoteDataSource.dataHandler.handleData("Click")
        videoViewModel.name = "Tell me why"
        Log.d(TAG, "video:${videoViewModel.hashCode()} - ${videoViewModel.name}")
        startActivity(Intent(this,SearchActivity::class.java))
    }

    fun onReplace(view: View) {
        Log.d(TAG, "onReplace")
        val currentFragment=supportFragmentManager.findFragmentById(R.id.fragment_container)
        val replaceFragment= if(currentFragment is MainFragment) subFragment else  mainFragment
        val fragmentTransition=supportFragmentManager.beginTransaction()
        fragmentTransition.replace(R.id.fragment_container,replaceFragment)
        fragmentTransition.commitNow()
    }
}