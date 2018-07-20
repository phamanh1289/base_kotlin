package com.appcyclone.basekotlin.data.network.api

import com.appcyclone.basekotlin.data.network.model.AlbumModel
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * * Created by Anh Pham on 07/19/2018.     **
 * * Copyright (c) 2018 by AppsCyclone      **
 */
interface AlbumApi {
    @GET("albums")
    fun getAlbumList(): Observable<MutableList<AlbumModel>>
}