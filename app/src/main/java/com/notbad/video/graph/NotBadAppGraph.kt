package com.notbad.video.graph

import android.content.Context
import com.notbad.video.MainActivity
import com.notbad.video.SearchActivity
import com.notbad.video.module.HandlerModule
import com.notbad.video.module.ServiceModule
import com.notbad.video.data.VideoRepository
import com.notbad.video.module.LoginModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [ServiceModule::class, HandlerModule::class, LoginModule::class])
@Singleton
interface NotBadAppGraph {

    @Component.Factory
    interface Factory{
        fun create(@BindsInstance context: Context): NotBadAppGraph
    }

    fun repository(): VideoRepository

    fun inject(mainActivity: MainActivity)

    fun inject(searchActivity: SearchActivity)

    fun registerLoginGraph():LoginGraph.Factory

}