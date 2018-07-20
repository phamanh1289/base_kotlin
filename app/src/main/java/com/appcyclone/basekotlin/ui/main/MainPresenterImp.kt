package com.appcyclone.basekotlin.ui.main

import android.util.Log
import com.appcyclone.basekotlin.data.network.api.PostApi
import com.appcyclone.basekotlin.data.network.model.PostModel
import com.appcyclone.basekotlin.ui.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * * Created by Anh Pham on 07/19/2018.     **
 * * Copyright (c) 2018 by AppsCyclone      **
 */
class MainPresenterImp @Inject constructor(val postApi: PostApi) : BasePresenter<MainContract.MainView>(), MainContract.MainPresenter {

    override fun getListStories() {
        addSubscribe(
                postApi.getPostList().subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ list: List<PostModel>? ->
                            view?.loadListPost(list!!.take(2))
                        }, { error ->
                            Log.d("error", error.toString())
                        })
        )
    }
}