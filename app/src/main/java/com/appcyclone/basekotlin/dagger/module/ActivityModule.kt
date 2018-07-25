package com.appcyclone.basekotlin.dagger.module
import android.app.Activity
import android.content.Context
import com.appcyclone.basekotlin.dagger.scope.ActivityScope
import dagger.Module
import dagger.Provides

/**
 * * Created by Anh Pham on 07/10/2018.     **
 * * Copyright (c) 2018 by AppsCyclone      **
 */
@ActivityScope
@Module
class ActivityModule(private var activity: Activity) {

    @Provides
    fun provideContext(): Context {
        return activity
    }

    @Provides
    fun provideActivity(): Activity {
        return activity
    }
}