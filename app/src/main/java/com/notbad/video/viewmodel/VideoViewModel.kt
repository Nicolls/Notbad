package com.notbad.video.viewmodel

import com.notbad.video.data.VideoRepository
import javax.inject.Inject


class VideoViewModel @Inject constructor(val repository: VideoRepository) {
    var name:String?=null

}