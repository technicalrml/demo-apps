package com.sourcey.materiallogindemo.core.service

import android.app.Activity
import com.sourcey.tenant.core.retrofit.RetrofitClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface SiswaService {
    @GET("account/crud_siswa/{card_code}/{nis}")
    fun getSiswa(@Path("card_code") card_code: String, @Path("nis") nis: String): Call<ResponseBody>

    @GET("maestro/history_t/{siswa_id}")
    fun getTabungan(@Path("siswa_id") siswa_id: String) : Call<ResponseBody>

    @GET("account/absensi_r/{siswa_id}")
    fun getAbsensi(@Path("siswa_id") siswa_id: String) : Call<ResponseBody>

    companion object {
        fun create(activity: Activity) : SiswaService {
            return RetrofitClient.getClient(activity).create(SiswaService::class.java)
        }
    }
}