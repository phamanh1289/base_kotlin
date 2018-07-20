package com.appcyclone.basekotlin.ui.base

import android.app.Application
import com.appcyclone.basekotlin.dagger.component.ApplicationComponent
import com.appcyclone.basekotlin.dagger.component.DaggerApplicationComponent
import com.appcyclone.basekotlin.dagger.connect.ApiConfigType
import com.appcyclone.basekotlin.dagger.module.ApplicationModule
import com.appcyclone.basekotlin.dagger.module.NetworkModule

/**
 * * Created by Anh Pham on 07/10/2018.     **
 * * Copyright (c) 2018 by AppsCyclone      **
 */
class BaseApplication : Application() {
    companion object {
        lateinit var instance: BaseApplication private set
    }

    lateinit var baseApp: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        setupInjector()
    }


    private fun setupInjector() {
        baseApp = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .networkModule(NetworkModule(ApiConfigType.DEVELOP))
                .build()
    }

    fun getApplicationComponent(): ApplicationComponent {
        return baseApp
    }
}