package com.notbad.video.module

import com.notbad.video.data.DataHandlerImpl2
import com.notbad.video.data.IDataHandler
import dagger.Binds
import dagger.Module

@Module
abstract class HandlerModule {
    @Binds
    abstract fun providerDataHandler(dataHandler: DataHandlerImpl2): IDataHandler
}