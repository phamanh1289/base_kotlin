package com.appcyclone.basekotlin.data.network.realm.album

import io.realm.RealmObject

open class AlbumRealmModel (var id: Int = 0, var userId: Int = 0, var title: String = "") : RealmObject()