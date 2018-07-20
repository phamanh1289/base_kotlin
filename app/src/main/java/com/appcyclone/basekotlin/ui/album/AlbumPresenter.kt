package com.appcyclone.basekotlin.ui.album

import android.util.Log
import com.appcyclone.basekotlin.data.network.api.AlbumApi
import com.appcyclone.basekotlin.data.network.model.AlbumModel
import com.appcyclone.basekotlin.ui.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * * Created by Anh Pham on 07/19/2018.     **
 * * Copyright (c) 2018 by AppsCyclone      **
 */
class AlbumPresenter @Inject constructor(val albumApi: AlbumApi) : BasePresenter<AlbumContract.AlbumView>(), AlbumContract.AlbumPresenter {


    override fun getAllAlbum() {
        addSubscribe(albumApi.getAlbumList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ list: MutableList<AlbumModel>? ->
                    view?.loadAllAlbum(list!!)
                }, { error ->
                    Log.d("error", error.toString())
                }))
    }
}