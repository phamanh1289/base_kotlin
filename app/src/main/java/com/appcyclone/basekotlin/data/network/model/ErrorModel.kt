package com.appcyclone.basekotlin.data.network.model

import android.text.TextUtils
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import okhttp3.Response

/**
 * * Created by Anh Pham on 07/16/2018.     **
 * * Copyright (c) 2018 by AppsCyclone      **
 */
class ErrorModel {

    @SerializedName("message")
    var message: String? = null
        get() = if (TextUtils.isEmpty(field)) "Error from server, Please try again!" else field
    @SerializedName("code")
    var code = -1

    override fun toString(): String {
        return Gson().toJson(this)
    }

    companion object {

        fun parseData(json: String): ErrorModel {
            var errorModel: ErrorModel
            try {
                val gson = Gson()
                errorModel = gson.fromJson(json, ErrorModel::class.java)
                if (errorModel != null) return errorModel
                errorModel = ErrorModel()
                errorModel.message = json
            } catch (e: Exception) {
                errorModel = ErrorModel()
                errorModel.message = json
            }
            return errorModel
        }

        fun getErrorString(response: Response): String {
            val errorModel = ErrorModel()
            errorModel.code = response.code()
            val body = response.body()!!.toString()
            if (!body.startsWith("<!DOCTYPE HTML")) {
                errorModel.message = body
            } else {
                errorModel.message = ""
            }
            return errorModel.toString()
        }
    }
}