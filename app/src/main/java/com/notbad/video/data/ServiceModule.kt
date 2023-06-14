package com.notbad.video.data

import android.content.Context
import com.notbad.video.network.RetrofitService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext

@Module
@InstallIn(ActivityComponent::class)
class ServiceModule {
    @Provides
    fun bindRetrofitService(@ActivityContext context:Context):RetrofitService {
        return RetrofitService(context)
    }
}