package com.appcyclone.basekotlin.dagger.module

import android.app.Application
import com.appcyclone.basekotlin.dagger.connect.ApiConfig
import com.appcyclone.basekotlin.dagger.connect.ApiConfigType
import com.appcyclone.basekotlin.dagger.scope.AppScope
import com.appcyclone.basekotlin.data.network.api.AlbumApi
import com.appcyclone.basekotlin.data.network.api.PostApi
import com.appcyclone.basekotlin.data.network.model.ErrorModel
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.io.IOException
import java.util.concurrent.TimeUnit

/**
 * * Created by Anh Pham on 07/19/2018.     **
 * * Copyright (c) 2018 by AppsCyclone      **
 */
@Module
@AppScope
class NetworkModule(private val mType: ApiConfigType) {
    private val DISK_CACHE_SIZE = (50 * 1024 * 1024).toLong() // 50MB

    @Provides
    fun provideOkHttpClient(app: Application): OkHttpClient {
        val cacheDir = File(app.cacheDir, "http")
        return OkHttpClient.Builder()
                .readTimeout(1, TimeUnit.MINUTES)
                .connectTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)
                .cache(okhttp3.Cache(cacheDir, DISK_CACHE_SIZE))
                .build()
    }

    @Provides
    fun createRetrofit(): Retrofit {
        val httpClient = OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(4, TimeUnit.MINUTES)
                .writeTimeout(4, TimeUnit.MINUTES)
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        httpClient.addInterceptor(ApiCustomInterceptor())
        return Retrofit.Builder()
                .baseUrl(ApiConfig.createConnectionDetail(mType).baseURL.toString())
                .client(httpClient.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder()
                        .setLenient()
                        .create()))
                .build()
    }

    class ApiCustomInterceptor : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            val response = chain.proceed(chain.request())
            if (response.code() >= 400) {
                throw IOException(ErrorModel.getErrorString(response))
            }
            val stringResponse: String = response.body()!!.string()
            var newString = ""
            try {
                if (stringResponse.startsWith("["))
                    newString = stringResponse
                else {
                    val root = JSONObject(stringResponse.trim())
                    if (root.has("error"))
                        throw IOException(root.getString("error"))
                    newString = if (root.has("data")) root.opt("data").toString() else stringResponse
                }
                return response.newBuilder()
                        .message("Successful")
                        .body(ResponseBody.create(response.body()!!.contentType(), newString))
                        .build()
            } catch (e: Exception) {
                throw IOException(e.message)
            }
        }
    }

    @Provides
    fun provideStoryApi(restAdapter: Retrofit): PostApi {
        return restAdapter.create(PostApi::class.java)
    }

    @Provides
    fun provideAlbumApi(restAdapter: Retrofit): AlbumApi {
        return restAdapter.create(AlbumApi::class.java)
    }
}