package com.appcyclone.basekotlin.ui.album

import com.appcyclone.basekotlin.data.network.model.AlbumModel
import com.appcyclone.basekotlin.ui.base.BaseView

/**
 * * Created by Anh Pham on 07/19/2018.     **
 * * Copyright (c) 2018 by AppsCyclone      **
 */
interface AlbumContract {
    interface AlbumView : BaseView {
        fun loadAllAlbum(list: MutableList<AlbumModel>)
    }

    interface AlbumPresenter {
        fun getAllAlbum()
    }
}