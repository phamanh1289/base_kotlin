package com.appcyclone.basekotlin.dagger.connect

/**
 * * Created by Anh Pham on 07/16/2018.     **
 * * Copyright (c) 2018 by AppsCyclone      **
 */
class ApiConfigDetail {
    var baseURL: String? = null
    var apiKey: String? = null
        private set

    fun setmApiKey(mApiKey: String) {
        this.apiKey = mApiKey
    }
}