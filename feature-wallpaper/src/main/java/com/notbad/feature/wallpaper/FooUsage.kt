package com.notbad.feature.wallpaper

import android.content.Context
import com.notbad.lib.common.IFoo
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent

private const val TAG = "FooUsage"
object FooUsage {
    /**
     * 先提供我们的EntryPoint，以便可以让Hilt帮我们生成需要的依赖对象
     */
    @EntryPoint
    @InstallIn(SingletonComponent::class)
    interface FooEntryPoint{
        fun getFoo():IFoo
    }

    fun fooSay(context:Context){
        val fooEntryPoint:FooEntryPoint = EntryPointAccessors.fromApplication(context.applicationContext)
        val foo = fooEntryPoint.getFoo()
        foo.say("enough")
    }


}