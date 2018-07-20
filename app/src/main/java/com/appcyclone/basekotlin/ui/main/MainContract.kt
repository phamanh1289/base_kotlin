package com.appcyclone.basekotlin.ui.main

import com.appcyclone.basekotlin.data.network.model.PostModel
import com.appcyclone.basekotlin.ui.base.BaseView

/**
 * * Created by Anh Pham on 07/19/2018.     **
 * * Copyright (c) 2018 by AppsCyclone      **
 */
interface MainContract {
    interface MainView : BaseView {
        fun loadListPost(list: List<PostModel>)
    }

    interface MainPresenter {
        fun getListStories()
    }
}