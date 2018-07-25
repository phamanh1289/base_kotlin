package com.mobologicplus.kotlinmvpandroid.db

import com.appcyclone.basekotlin.data.network.model.AlbumModel
import io.reactivex.Observable

/**
 * Created by sunil on 5/30/2017.
 */
interface DbSource {

    fun saveAlbumList(Album: AlbumModel)

    fun loadAlbum() : Observable<List<AlbumModel>>

    fun detailAlbum(AlbumId : String) : Observable<AlbumModel>

    fun deleteAlbum(Album: AlbumModel) : Observable<Boolean>
}