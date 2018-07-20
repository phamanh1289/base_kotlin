package com.appcyclone.basekotlin.ui.base

/**
 * * Created by Anh Pham on 07/19/2018.     **
 * * Copyright (c) 2018 by AppsCyclone      **
 */
interface IPresenter<V : BaseView> {
    val isViewAttach: Boolean
    fun onAttach(mvpView: V)
    fun onDetach()
}