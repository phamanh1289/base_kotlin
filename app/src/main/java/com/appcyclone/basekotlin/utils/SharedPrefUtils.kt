package com.appcyclone.basekotlin.utils

import android.content.Context
import android.content.SharedPreferences

/**
 * * Created by Anh Pham on 07/10/2018.     **
 * * Copyright (c) 2018 by AppsCyclone      **
 */
class SharedPrefUtils constructor(app: Context?) {

    companion object {
        private const val SHARED_PREF_NAME = "base_kotlin"
        private const val DEVICE_TOKEN = "device_token"
    }

    private inline fun SharedPreferences.put(body: SharedPreferences.Editor.() -> Unit) {
        val editor = this.edit()
        editor.body()
        editor.apply()
    }

    private val sharedPreferences by lazy(LazyThreadSafetyMode.NONE) {
        app?.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
    }
    var deviceToken: String
        get() = sharedPreferences?.getString(DEVICE_TOKEN, "")!!
        set(value) = sharedPreferences?.put { putString(DEVICE_TOKEN, value) }!!
}