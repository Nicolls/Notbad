package com.notbad.hilt.data

import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ActivityRetainedScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton

class VideoRepository @Inject constructor(
    val localDataSource: LocalDataSource,
    val dataHandler: IDataHandler
) {

}