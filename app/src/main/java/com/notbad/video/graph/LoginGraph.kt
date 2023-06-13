package com.notbad.video.graph

import com.notbad.video.MainActivity
import com.notbad.video.data.VideoRepository
import com.notbad.video.view.MainFragment
import com.notbad.video.view.SubFragment
import dagger.Subcomponent
import javax.inject.Inject
import javax.inject.Scope

@Scope
@Retention(value = AnnotationRetention.RUNTIME)
annotation class ActivityScopeC

@ActivityScopeC
@Subcomponent
interface LoginGraph {
    @Subcomponent.Factory
    interface Factory{
        fun create():LoginGraph
    }

    fun inject(mainActivity: MainActivity)

    fun inject(mainFragment: MainFragment)

    fun inject(subFragment: SubFragment)

}
@ActivityScopeC
class LoginViewModel @Inject constructor(val repository: VideoRepository) {
}