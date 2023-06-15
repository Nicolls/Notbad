package com.notbad.video.viewmodel

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.notbad.video.data.IDataCallBack
import com.notbad.video.data.VideoRepository
import com.notbad.video.network.RetrofitService
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

private const val TAG = "VideoViewModel"
@HiltViewModel
class VideoViewModel @Inject constructor(private val savedStateHandle: SavedStateHandle,
                                         val repository: VideoRepository) : ViewModel() {
    @Inject
    lateinit var retrofitService: RetrofitService


    fun printCode(){
        Log.d(TAG, "hashCode:${repository.hashCode()}")
    }
    override fun onCleared() {
        super.onCleared()
    }
}