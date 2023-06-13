package com.notbad.video.data

import javax.inject.Inject

class VideoRepository @Inject constructor(
    val localDataSource: LocalDataSource,
    val remoteDataSource: RemoteDataSource
) {
}