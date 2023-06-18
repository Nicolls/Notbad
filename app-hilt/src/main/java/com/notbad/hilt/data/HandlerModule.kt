package com.notbad.hilt.data

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class HandlerModule {
    @Binds
    abstract fun bindDataHandler(dataHandler: DataHandlerImpl): IDataHandler


}