package com.notbad.feature.video

import android.util.Log
import com.notbad.lib.common.IFoo
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private const val TAG = "VideoFoo"

class VideoFoo : IFoo {
    override fun say(msg: String) {
        Log.d(TAG, "video foo")
    }

    /**
     * 使用Providers
     */
    @Module
    @InstallIn(SingletonComponent::class)
    class VideoFooProvider {
        @Provides
        // @Singleton // 如果你希望它是一个单例，可以加上这个。
        fun providerFoo(): IFoo {
            return VideoFoo()
        }
    }
    /**
     * 使用binds
     */
//    @Module
//    @InstallIn(SingletonComponent::class)
//    abstract class VideoFoolProvider {
//        @Binds
//        abstract fun bindFoo(foo:VideoFoo):IFoo
//    }

}