package com.sourcey.materiallogindemo.beranda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sourcey.materiallogindemo.adapter.AdapterTabungan
import com.sourcey.materiallogindemo.core.service.SiswaService
import com.sourcey.materiallogindemo.databinding.ActivityTabunganBinding
import com.sourcey.materiallogindemo.models.TabunganModels
import com.sourcey.tenant.core.helper.Connection
import okhttp3.ResponseBody
import org.json.JSONArray
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Tabungan : AppCompatActivity() {
    lateinit var binding: ActivityTabunganBinding
    lateinit var connection: Connection
    lateinit var siswaService: SiswaService
    var siswaId = ""
    var tabunganModels: ArrayList<TabunganModels> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTabunganBinding.inflate(layoutInflater)
        setContentView(binding.root)
        connection = Connection(this)
        siswaService = SiswaService.create(this)
        siswaId = intent.getStringExtra("siswa_id").toString()
        getTabungan()
        binding.swipe.setOnRefreshListener {
            getTabungan()
        }
        binding.btnBack.setOnClickListener {
            super.onBackPressed()
        }
    }

    fun getTabungan(){
        tabunganModels.clear()
        if (connection.isConnectionInternet()){
            siswaService.getTabungan(siswaId).enqueue(object : Callback<ResponseBody>{
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                   if (response.isSuccessful){
                       var responseBody = response.body()?.string()
                       var jsonArray = JSONArray(responseBody)
                       var newData = TabunganModels()
                       for (i in 0 until jsonArray.length()){
                           var item = jsonArray.getJSONObject(i)
                           if (item.has("date_transaksi")) newData.tanggal = item.getString("date_transaksi")
                           if (item.has("debit")) newData.debit = item.getString("debit")
                           if (item.has("credit")) newData.kredit = item.getString("credit")
                           if (item.has("saldo")) newData.saldo = item.getString("saldo")
                           tabunganModels.add(newData)
                       }

                       var layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(this@Tabungan)
                       binding.rvTabungan.layoutManager = layoutManager
                       var adapterTabungan : AdapterTabungan = AdapterTabungan(tabunganModels)
                       binding.rvTabungan.adapter = adapterTabungan
                       binding.swipe.isRefreshing = false
                       adapterTabungan.notifyDataSetChanged()
                   }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Log.d("TAG", "onFailure: "+ t.printStackTrace())
                }
            })
        }
    }
}