package com.appcyclone.basekotlin.dagger.module

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val baseApplication: Application) {

    @Provides
    @Singleton
    fun provideApplicationContext(): Application {
        return baseApplication
    }
}