package com.notbad.hilt.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.notbad.lib.common.LogUtils
import com.notbad.hilt.R
import com.notbad.hilt.data.IDataHandler
import com.notbad.hilt.network.RetrofitService
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

private const val TAG = "MainFragment"

@AndroidEntryPoint
class MainFragment : Fragment() {
    @Inject
    lateinit var retrofitService: RetrofitService
    @Inject
    lateinit var dataHandler: IDataHandler

    override fun onAttach(context: Context) {
        super.onAttach(context)
        LogUtils.d(TAG, "onAttach context")

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LogUtils.d(TAG, "onCreate ${retrofitService.hashCode()}")
        dataHandler.handleData("hello main fragment")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        LogUtils.d(TAG, "onCreateView")
        return inflater.inflate(R.layout.fragment_main, container, false)
    }
}