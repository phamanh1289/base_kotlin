package com.appcyclone.basekotlin.dagger.component

import com.appcyclone.basekotlin.dagger.module.ApplicationModule
import com.appcyclone.basekotlin.dagger.module.NetworkModule
import com.appcyclone.basekotlin.dagger.scope.AppScope
import dagger.Component
import javax.inject.Singleton


/**
 * * Created by Anh Pham on 07/10/2018.     **
 * * Copyright (c) 2018 by AppsCyclone      **
 */
@AppScope
@Singleton
@Component(modules = [ApplicationModule::class, NetworkModule::class])
interface ApplicationComponent {
    fun getActivityComponent() : ActivityComponent
}