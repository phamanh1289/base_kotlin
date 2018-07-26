package com.appcyclone.basekotlin.ui.album

import com.appcyclone.basekotlin.data.network.api.AlbumApi
import com.appcyclone.basekotlin.data.network.model.AlbumModel
import com.appcyclone.basekotlin.data.network.realm.album.RealmAlbumManager
import com.appcyclone.basekotlin.ui.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * * Created by Anh Pham on 07/19/2018.     **
 * * Copyright (c) 2018 by AppsCyclone      **
 */
class AlbumPresenter @Inject constructor(val albumApi: AlbumApi) : BasePresenter<AlbumContract.AlbumView>(), AlbumContract.AlbumPresenter {

    fun getDataAlbum() {
        if (RealmAlbumManager().getItemCount() != 0)
            getAllAlbumDB()
        else getAllAlbum()
    }

    override fun getAllAlbumDB() {
        addSubscribe(RealmAlbumManager().readAlbum()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ list: MutableList<AlbumModel>? ->
                    view?.loadAllAlbum(list!!)
                }, {
                    view?.handleError()
                }))
    }

    override fun getAllAlbum() {
        addSubscribe(albumApi.getAlbumList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ list: MutableList<AlbumModel>? ->
                    view?.loadAllAlbum(list!!.take(5) as MutableList<AlbumModel>)
                    list?.take(5)?.forEach {
                        RealmAlbumManager().insertOrUpdateAlbum(it)
                    }
                }, {
                    view?.handleError()
                }))
    }

    override fun deleteAlbum(model: AlbumModel) {
        addSubscribe(RealmAlbumManager().deleteAlbum(model)
                .subscribe({
                    if (it) view?.loadAfterDeleteAlbum()
                }, {
                    view?.handleError()
                })
        )
    }
}