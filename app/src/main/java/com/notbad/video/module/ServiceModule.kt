package com.notbad.video.module

import com.notbad.video.network.RetrofitService
import dagger.Module
import dagger.Provides

@Module
class ServiceModule {
    @Provides
    fun providerRetrofitService(): RetrofitService {
        return RetrofitService
    }
}