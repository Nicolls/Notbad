package com.notbad.video.data

import com.notbad.video.network.RetrofitService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
abstract class HandlerModule {
    @Binds
    abstract fun bindDataHandler(dataHandler:DataHandlerImpl):IDataHandler

    @Binds
    abstract fun bindDataCallBack(dataCallBack:DataCallBackOnMain):IDataCallBack

}