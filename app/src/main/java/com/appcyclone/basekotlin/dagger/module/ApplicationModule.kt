package com.appcyclone.basekotlin.dagger.module

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * * Created by Anh Pham on 07/10/2018.     **
 * * Copyright (c) 2018 by AppsCyclone      **
 */
@Module
class ApplicationModule(private val baseApplication: Application) {

    @Provides
    @Singleton
    fun provideApplicationContext(): Application {
        return baseApplication
    }
}