package com.appcyclone.basekotlin.dagger.connect

/**
 * * Created by Anh Pham on 07/16/2018.     **
 * * Copyright (c) 2018 by AppsCyclone      **
 */
object ApiConfig {
    fun createConnectionDetail(typeApi: ApiConfigType?): ApiConfigDetail {
        var type = typeApi
        val connection = ApiConfigDetail()
        if (type == null) {
            type = ApiConfigType.DEVELOP
        }
        when (type) {
            ApiConfigType.DEVELOP -> connection.baseURL = "https://jsonplaceholder.typicode.com/"
            ApiConfigType.STAGING -> connection.baseURL = "http://windhost.esy.es/"
            ApiConfigType.PRELIVE -> {
                connection.baseURL = "https://prelive.fxchange.rmlbs.co/api/v1/"
                connection.setmApiKey("DEVACCRMLEXCHANGE03052017")
            }
            ApiConfigType.LIVE -> {
                connection.baseURL = "https://live.fxchange.sg/api/v1/"
                connection.setmApiKey("LIVEACCRMLEXCHANGE03032017")
            }
        }
        return connection
    }
}