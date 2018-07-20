package com.appcyclone.basekotlin.dagger.module
import android.app.Activity
import android.content.Context
import com.appcyclone.basekotlin.dagger.scope.ActivityScope
import dagger.Module
import dagger.Provides

/**
 * Created by sunil on 5/30/2017.
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