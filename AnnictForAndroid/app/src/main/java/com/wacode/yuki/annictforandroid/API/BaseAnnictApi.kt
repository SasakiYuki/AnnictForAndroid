package com.wacode.yuki.annictforandroid.API

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by yuki on 2016/07/25.
 */
object BaseAnnictApi {
    private val ENDPOINT = "https://api.annict.com/"

    val restClient: Retrofit
        get() = Retrofit.Builder().client(getClient()).baseUrl(ENDPOINT).addConverterFactory(GsonConverterFactory.create(Gson())).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build()

    private fun getClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }
}