package com.appcyclone.basekotlin.data.network.realm.album

import com.appcyclone.basekotlin.data.network.model.AlbumModel
import io.reactivex.Observable
import io.realm.Realm

open class RealmAlbumPresenter : RealmAlbumContract {

    private var realm: Realm? = null

    override fun readAlbum(): Observable<MutableList<AlbumModel>> {
        val list: MutableList<AlbumModel> = ArrayList()

        try {
            realm?.executeTransaction {
                realm?.where(AlbumRealmModel::class.java)?.findAllAsync()?.map {
                    list.add(AlbumModel(id = it.id, userId = it.userId, title = it.title))
                }
            }
            realm?.close()
        } finally {
            realm?.cancelTransaction()
        }
        return Observable.just(list)
    }

    override fun writeAlbum(mode: AlbumModel) {
        realm = Realm.getDefaultInstance()
        try {
            realm?.executeTransaction {
                val item = realm?.createObject(AlbumRealmModel::class.java)
                item?.id = mode.id
                item?.title = mode.title
                item?.userId = mode.userId
                realm?.insert(item)
            }
            realm?.close()
        } finally {
            realm?.cancelTransaction()
        }
    }

    override fun findId(id: Int): Observable<AlbumRealmModel?> {
        realm = Realm.getDefaultInstance()
        var obserableAlbum: AlbumRealmModel? = null
        try {
            realm?.executeTransaction {
                obserableAlbum = realm?.where(AlbumRealmModel::class.java)?.equalTo("id", id)?.findFirst()
            }
            realm?.close()
            return Observable.just(obserableAlbum)
        } finally {
            realm?.cancelTransaction()
        }
    }

//    override fun deleteAlbum(id: Int): Observable<Boolean> {
//
//    }

    fun getItemCount(): Int {
        var count: Int? = 0
        realm = Realm.getDefaultInstance()
        try {
            realm?.executeTransaction {
                count = realm?.where(AlbumRealmModel::class.java)?.findAll()?.size
            }
            realm?.close()
        } finally {
            realm?.cancelTransaction()
        }
        return count ?: 0
    }
}