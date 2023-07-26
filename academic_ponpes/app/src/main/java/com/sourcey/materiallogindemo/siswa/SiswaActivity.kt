package com.sourcey.materiallogindemo.siswa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sourcey.materiallogindemo.MainActivity
import com.sourcey.materiallogindemo.adapter.AdapterSiswa
import com.sourcey.materiallogindemo.R
import com.sourcey.materiallogindemo.core.db.DbHelperSiswa
import com.sourcey.materiallogindemo.databinding.ActivitySiswaBinding
import com.sourcey.materiallogindemo.databinding.DialogAddSiswaBinding
import com.sourcey.materiallogindemo.models.SiswaModels
import com.sourcey.tenant.core.helper.SessionManager
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog

class SiswaActivity : AppCompatActivity() {
    lateinit var binding: ActivitySiswaBinding
    lateinit var dbHelperSiswa: DbHelperSiswa
    lateinit var dialog: BottomSheetDialog
    lateinit var mGoogleSignInClient: GoogleSignInClient
    lateinit var sessionManager: SessionManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySiswaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val toolbar : Toolbar = binding.toolbar as Toolbar
        setSupportActionBar(toolbar)
        sessionManager = SessionManager(this)
        dbHelperSiswa = DbHelperSiswa(this)
        getSiswa()
        binding.addSiswa.setOnClickListener {
            openDialogAddSiswa()
        }
        binding.swipe.setOnRefreshListener {
            getSiswa()
        }
        configureLoginWithGoogle()
    }

    fun configureLoginWithGoogle(){
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

    }

    fun loading(boolean: Boolean) {
        binding.swipe.isRefreshing = boolean
    }

    fun getSiswa() {
        loading(true)
        val siswaList = dbHelperSiswa.getAllSiswa()
        Log.d("TAG", "getSiswa: " + siswaList.size)
        var layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        binding.rvSiswa.layoutManager = layoutManager
        var adapterSiswa: AdapterSiswa = AdapterSiswa(this, siswaList)
        binding.rvSiswa.adapter = adapterSiswa
        loading(false)

        adapterSiswa.setOnClickDelete {
            deleteSiswa(it.id)
        }

        adapterSiswa.setOnClickUpdate { it ->
            updateSiswa(it.id, it.card_code, it.nama, it.nis)
        }

        adapterSiswa.setOnClickIntent {
            val intent : Intent = Intent(this@SiswaActivity, MainActivity::class.java)
            intent.putExtra("card_code", it.card_code)
            intent.putExtra("nis", it.nis)
            startActivity(intent)
        }

        adapterSiswa.notifyDataSetChanged()
    }

    fun updateSiswa(id: Int, cardCode: String, nama: String, nis: String) {
        dialog = BottomSheetDialog(this, R.style.NoBackgroundDialogTheme)
        var dialogBinding = DialogAddSiswaBinding.inflate(LayoutInflater.from(this))
        dialogBinding.etCardCode.setText(cardCode)
        dialogBinding.etNama.setText(nama)
        dialogBinding.etNis.setText(nis)
        dialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
        dialog.setContentView(dialogBinding.root)
        dialog.behavior.isDraggable = true
        dialogBinding.btnSimpanSiswa.setOnClickListener {
            var status = dbHelperSiswa.updateSiswa(
                id = id,
                card_code = dialogBinding.etCardCode.text.toString(),
                nama = dialogBinding.etNama.text.toString(),
                nis = dialogBinding.etNis.text.toString()
            )
            if (status > -1) {
                Toast.makeText(this, "Data Berhasil di rubah", Toast.LENGTH_SHORT).show()
                Log.d("TAG", "updateSiswa: " + status)
                dialog.dismiss()
            }

        }
        dialog.show()
    }

    private fun deleteSiswa(id: Int) {
        if (id == null) return
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Apakah anda ingin menghapus siswa ?")
        builder.setCancelable(true)
        builder.setPositiveButton("Ya") { dialog, _ ->
            dbHelperSiswa.deleteSiswa(id)
            dialog.dismiss()
        }
        builder.setNegativeButton("Tidak") { dialog, _ ->
            dialog.dismiss()
        }
        builder.show()
    }

    private fun openDialogAddSiswa() {
        dialog = BottomSheetDialog(this, R.style.NoBackgroundDialogTheme)
        var dialogBinding = DialogAddSiswaBinding.inflate(LayoutInflater.from(this))
        dialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
        dialog.setContentView(dialogBinding.root)
        dialog.behavior.isDraggable = true
        dialogBinding.btnSimpanSiswa.setOnClickListener {
            if (dialogBinding.etNama.text.isEmpty() || dialogBinding.etCardCode.text.isEmpty() || dialogBinding.etNis.text.isEmpty()) {
                Toast.makeText(this, "Mohon Lengkapi data anda", Toast.LENGTH_SHORT).show()
            } else {
                val siswa = SiswaModels(
                    card_code = dialogBinding.etCardCode.text.toString(),
                    nis = dialogBinding.etNis.text.toString(),
                    nama = dialogBinding.etNama.text.toString()
                )
                val status = dbHelperSiswa.insertSiswa(siswa)
                if (status > -1) {
                    Toast.makeText(this, "Data Tersimpan", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                }
            }
        }
        dialog.show()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_siswa, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.logout ->{
                mGoogleSignInClient.signOut()
                sessionManager.logoutUser()
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}