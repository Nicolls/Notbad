package com.notbad.video.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.notbad.lib.common.LogUtils
import com.notbad.video.R
import com.notbad.video.data.IDataHandler
import com.notbad.video.network.RetrofitService
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

private const val TAG = "MainFragment"

@AndroidEntryPoint
class SubFragment : Fragment() {
    @Inject
    lateinit var dataHandler: IDataHandler
    @Inject
    lateinit var retrofitService: RetrofitService
    override fun onAttach(context: Context) {
        super.onAttach(context)
        LogUtils.d(TAG, "onAttach context")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LogUtils.d(TAG, "onCreate ${retrofitService.hashCode()}")
        dataHandler.handleData("hello sub fragment")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        LogUtils.d(TAG, "onCreateView")
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sub, container, false)
    }
}