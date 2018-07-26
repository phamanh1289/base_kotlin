package com.appcyclone.basekotlin.data.network.realm.album

import com.appcyclone.basekotlin.data.network.model.AlbumModel
import io.reactivex.Observable

interface RealmAlbumSource {

    fun readAlbum(): Observable<MutableList<AlbumModel>>

    fun insertOrUpdateAlbum(mode: AlbumModel)

    fun findId(id: Int):Observable<AlbumRealmModel?>

    fun deleteAlbum(model: AlbumModel) : Observable<Boolean>
}