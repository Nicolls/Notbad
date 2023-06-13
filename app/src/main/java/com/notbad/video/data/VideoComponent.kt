package com.notbad.video.data

import dagger.Component
import javax.inject.Singleton

@Component
@Singleton
interface VideoComponent {
    fun repository():VideoRepository
}