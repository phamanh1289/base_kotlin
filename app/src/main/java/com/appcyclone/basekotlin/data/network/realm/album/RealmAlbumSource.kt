package com.appcyclone.basekotlin.data.network.realm.album

import com.appcyclone.basekotlin.data.network.model.AlbumModel
import io.reactivex.Observable

interface RealmAlbumContract {

    fun readAlbum(): Observable<MutableList<AlbumModel>>

    fun writeAlbum(mode: AlbumModel)

    fun findId(id: Int):Observable<AlbumRealmModel?>

//    fun deleteAlbum(id: Int) : Observable<Boolean>
//
//    fun updateAlbum()
}