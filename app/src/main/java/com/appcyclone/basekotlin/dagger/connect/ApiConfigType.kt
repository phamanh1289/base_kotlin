package com.appcyclone.basekotlin.dagger.connect

/**
 * * Created by Anh Pham on 07/16/2018.     **
 * * Copyright (c) 2018 by AppsCyclone      **
 */
enum class ApiConfigType(var title: String?) {
    STAGING("staging"),
    DEVELOP("develop"),
    PRELIVE("prelive"),
    LIVE("live")
}