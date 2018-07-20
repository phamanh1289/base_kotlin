package com.appcyclone.basekotlin.ui.base

/**
 * * Created by Anh Pham on 07/10/2018.     **
 * * Copyright (c) 2018 by AppsCyclone      **
 */
interface BaseView {
    fun showLoading()

    fun hideLoading()

    fun isNetworkConnected(): Boolean

    fun addFragment(fragment: BaseFragment, isAddToBackStack: Boolean, isAnimation: Boolean)

    fun replaceFragment(fragment: BaseFragment, isAddToBackStack: Boolean, isAnimation: Boolean)
}