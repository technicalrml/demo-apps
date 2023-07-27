package com.sourcey.materiallogindemo.core.service

import android.app.Activity
import com.sourcey.materiallogindemo.core.retrofit.RetrofitClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface AuthService {
    @GET("account/user_gs/{firstname}/{lastname}/{username}/{email}/{uid}/gmail")
    fun loginWithSosmed(
        @Path("firstname") firstName: String,
        @Path("lastname") lastname: String,
        @Path("username") username: String,
        @Path("email") email: String,
        @Path("uid") uid:String
    ): Call<ResponseBody>


    companion object {
        fun create(activity: Activity) : AuthService {
            return RetrofitClient.getClient(activity).create(AuthService::class.java)
        }
    }
}