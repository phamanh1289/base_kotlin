package com.appcyclone.basekotlin.data.network.model

/**
 * * Created by Anh Pham on 07/10/2018.     **
 * * Copyright (c) 2018 by AppsCyclone      **
 */
data class PostModel(val id: Int, val userId: Int, val title: String, val body: String) : BaseModel()
