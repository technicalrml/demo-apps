package com.sourcey.materiallogindemo

import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.View
import com.sourcey.materiallogindemo.beranda.Absensi
import com.sourcey.materiallogindemo.beranda.Tabungan
import com.sourcey.materiallogindemo.core.service.SiswaService
import com.sourcey.materiallogindemo.databinding.ActivityMainBinding
import com.sourcey.tenant.core.helper.Connection
import com.sourcey.tenant.core.helper.Currency
import com.sourcey.tenant.core.helper.SessionManager
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(),View.OnClickListener {
    lateinit var binding: ActivityMainBinding
    lateinit var sessionManager: SessionManager
    lateinit var connection: Connection
    lateinit var siswaService: SiswaService
    var cardCode: String = ""
    var nis: String = ""
    var siswaId: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setOnClicks()
        sessionManager = SessionManager(this)
        connection = Connection(this)
        siswaService = SiswaService.create(this)
        cardCode = intent.getStringExtra("card_code").toString()
        nis = intent.getStringExtra("nis").toString()
        getSiswaByNis(cardCode, nis)
        binding.swipe.setOnRefreshListener {
            getSiswaByNis(cardCode, nis)
        }
    }

    fun loading(boolean: Boolean) {
        binding.swipe.isRefreshing = boolean
    }

    fun getSiswaByNis(cardCode: String, nis: String) {

        siswaService.getSiswa(cardCode, nis).enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {

                var responseBody = response.body()?.string()
                var jsonObject = JSONObject(responseBody)
                if(jsonObject.has("nama")) binding.tvName.text = "Nama : " + jsonObject.getString("nama")
                if(jsonObject.has("kelas")) binding.tvKelas.text = "Kelas : " + jsonObject.getString("kelas")
                binding.tvNisn.text = "NISN : " + nis
                if(jsonObject.has("tgl_lahir")) binding.tvTtl.text = jsonObject.getString("tgl_lahir")
                if(jsonObject.has("saldo")) binding.tvSaldo.text = "Rp. " + Currency.nominal(jsonObject.getString("saldo"))
                if (jsonObject.has("siswa_id")) siswaId = jsonObject.getString("siswa_id")
                var photo = ""
                if (jsonObject.has("photo")) photo = jsonObject.getString("photo")
                val imageBytes = Base64.decode(photo, Base64.DEFAULT)
                val decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
                binding.ivProfile.setImageBitmap(decodedImage)
                loading(false)
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.d("TAG", "onFailure: " + t.message)
            }
        })
    }

    private fun setOnClicks(){
        binding.btnInfo.setOnClickListener(this)
        binding.btnAbsensi.setOnClickListener(this)
        binding.btnTabungan.setOnClickListener(this)
        binding.btnTagihan.setOnClickListener(this)
        binding.btnNilai.setOnClickListener(this)
        binding.btnKembali.setOnClickListener(this)
    }
    override fun onClick(v: View) {
        when(v.id){
            R.id.btn_info -> null
            R.id.btn_absensi -> intentAbsensi()
            R.id.btn_tabungan -> intentTabungan()
            R.id.btn_tagihan -> null
            R.id.btn_nilai -> null
            R.id.btn_kembali -> null
        }
    }

    fun intentTabungan(){
        val intent: Intent = Intent(this@MainActivity, Tabungan::class.java)
        intent.putExtra("siswa_id", siswaId)
        startActivity(intent)
    }

    fun intentAbsensi(){
        val intent: Intent = Intent(this@MainActivity, Absensi::class.java)
        intent.putExtra("siswa_id", siswaId)
        startActivity(intent)
    }


}