package com.appcyclone.basekotlin.ui.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * * Created by Anh Pham on 07/19/2018.     **
 * * Copyright (c) 2018 by AppsCyclone      **
 */
open class BasePresenter<V : BaseView> : IPresenter<V> {

    var view: V? = null
        private set
    private val compositeDisposable: CompositeDisposable?

    override val isViewAttach: Boolean
        get() = view != null

    init {
        this.compositeDisposable = CompositeDisposable()
    }

    override fun onAttach(mvpView: V) {
        this.view = mvpView
    }

    override fun onDetach() {
        this.view = null
        unsubscribe()
    }

    private fun unsubscribe() {
        compositeDisposable?.dispose()
    }

    protected fun addSubscribe(disposable: Disposable) {
        compositeDisposable!!.add(disposable)
    }
}