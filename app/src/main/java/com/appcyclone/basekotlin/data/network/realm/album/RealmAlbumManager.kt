package com.appcyclone.basekotlin.data.network.realm.album

import com.appcyclone.basekotlin.data.network.model.AlbumModel
import io.reactivex.Observable
import io.realm.Realm

open class RealmAlbumManager : RealmAlbumSource {

    private var realm: Realm? = null

    override fun readAlbum(): Observable<MutableList<AlbumModel>> {
        val list: MutableList<AlbumModel> = ArrayList()
        try {
            realm = Realm.getDefaultInstance()
            realm?.executeTransaction {
                realm?.where(AlbumRealmModel::class.java)?.findAllAsync()?.map {
                    list.add(AlbumModel(id = it.id, userId = it.userId, title = it.title))
                }
            }
        } finally {
            realm?.close()
        }
        return Observable.just(list)
    }

    override fun insertOrUpdateAlbum(mode: AlbumModel) {
        try {
            realm = Realm.getDefaultInstance()
            realm?.executeTransaction {
                val item = realm?.createObject(AlbumRealmModel::class.java)
                item?.id = mode.id
                item?.title = mode.title
                item?.userId = mode.userId
                realm?.insertOrUpdate(item!!)
            }
        } finally {
            realm?.close()
        }
    }

    override fun findId(id: Int): Observable<AlbumRealmModel?> {
        try {
            realm = Realm.getDefaultInstance()
            var obserableAlbum: AlbumRealmModel? = null
            realm?.executeTransaction {
                obserableAlbum = realm?.where(AlbumRealmModel::class.java)?.equalTo("id", id)?.findFirst()
            }
            return Observable.just(obserableAlbum)
        } finally {
            realm?.close()
        }
    }

    override fun deleteAlbum(model: AlbumModel): Observable<Boolean> {
        try {
            var isCheck = false
            realm = Realm.getDefaultInstance()
            realm?.executeTransaction {
                val result = realm?.where(AlbumRealmModel::class.java)?.equalTo("id", model.id)?.findAll()
                isCheck = result?.size != 0
                if (isCheck) result?.deleteAllFromRealm()
            }
            return Observable.just(isCheck)
        } finally {
            realm?.close()
        }
    }

    fun getItemCount(): Int {
        var count: Int? = 0
        try {
            realm = Realm.getDefaultInstance()
            realm?.executeTransaction {
                count = realm?.where(AlbumRealmModel::class.java)?.findAll()?.size
            }
        } finally {
            realm?.close()
        }
        return count ?: 0
    }
}