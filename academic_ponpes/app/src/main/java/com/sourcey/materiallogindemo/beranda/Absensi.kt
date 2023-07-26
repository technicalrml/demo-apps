package com.sourcey.materiallogindemo.beranda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sourcey.materiallogindemo.adapter.AdapterAbsensi
import com.sourcey.materiallogindemo.core.service.SiswaService
import com.sourcey.materiallogindemo.databinding.ActivityAbsensiBinding
import com.sourcey.materiallogindemo.models.AbsensiModels
import com.sourcey.tenant.core.helper.Connection
import okhttp3.ResponseBody
import org.json.JSONArray
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Absensi : AppCompatActivity() {
    lateinit var binding: ActivityAbsensiBinding
    lateinit var connection: Connection
    lateinit var siswaService: SiswaService
    var siswaId = ""
    var absensiModels: ArrayList<AbsensiModels> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAbsensiBinding.inflate(layoutInflater)
        setContentView(binding.root)
        connection = Connection(this)
        siswaService =  SiswaService.create(this)
        siswaId = intent.getStringExtra("siswa_id").toString()
        getAbsensi()
        binding.swipe.setOnRefreshListener {
            getAbsensi()
        }
        binding.btnBack.setOnClickListener {
            super.onBackPressed()
        }
    }

    private fun getAbsensi(){
        absensiModels.clear()
        if (connection.isConnectionInternet()){
            siswaService.getAbsensi(siswaId).enqueue(object : Callback<ResponseBody>{
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    if (response.isSuccessful){
                        var responseBody = response.body()?.string()
                        var jsonArray =  JSONArray(responseBody)
                        var newData = AbsensiModels()
                        for (i in 0 until jsonArray.length()){
                            var item = jsonArray.getJSONObject(i)
                            if(item.has("tanggal")) newData.tanggal = item.getString("tanggal")
                            if(item.has("Masuk")) newData.masuk = item.getString("Masuk")
                            if(item.has("Pulang")) newData.pulang = item.getString("Pulang")
                            absensiModels.add(newData)
                        }
                        val layotManager: RecyclerView.LayoutManager = LinearLayoutManager(this@Absensi)
                        binding.rvAbsensi.layoutManager = layotManager
                        var adapterAbsensi: AdapterAbsensi = AdapterAbsensi(absensiModels)
                        binding.rvAbsensi.adapter = adapterAbsensi
                        binding.swipe.isRefreshing = false
                        adapterAbsensi.notifyDataSetChanged()
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Log.d("TAG", "onFailure: " + t.printStackTrace())
                }
            })
        }
    }
}