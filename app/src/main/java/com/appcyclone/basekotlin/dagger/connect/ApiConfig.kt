package com.appcyclone.basekotlin.dagger.connect

/**
 * * Created by Anh Pham on 07/16/2018.     **
 * * Copyright (c) 2018 by AppsCyclone      **
 */
object ApiConfig {
    fun createConnectionDetail(typeApi: ApiConfigType?): ApiConfigDetail {
        var type = typeApi ?: ApiConfigType.DEVELOP
        var url = ""
        var apiKey = ""
        when (type) {
            ApiConfigType.DEVELOP -> url = "https://jsonplaceholder.typicode.com/"
            ApiConfigType.STAGING -> url = "url staging"
            ApiConfigType.PRELIVE -> {
                url = "url"
                apiKey = "key"
            }
            ApiConfigType.LIVE -> {
                url = "url"
                apiKey = "key"
            }
        }
        return ApiConfigDetail(baseURL = url, apiKey = apiKey)
    }
}