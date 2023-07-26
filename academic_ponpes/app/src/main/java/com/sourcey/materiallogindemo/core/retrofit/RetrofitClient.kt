package com.sourcey.tenant.core.retrofit

import android.app.Activity
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    lateinit var retrofit: Retrofit
    var baseUrl = "https://ppkarangasem.com/"

    fun getClient(activity: Activity): Retrofit {
        var  interceptor : HttpLoggingInterceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        var client = OkHttpClient.Builder().addInterceptor(interceptor).build()
        retrofit = Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).client(client)
            .build()
        return retrofit
    }
}