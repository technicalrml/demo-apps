package com.sourcey.materiallogindemo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sourcey.materiallogindemo.databinding.LayoutAbsensiBinding
import com.sourcey.materiallogindemo.models.AbsensiModels

class AdapterAbsensi(var absensiModels: List<AbsensiModels>) : RecyclerView.Adapter<AdapterAbsensi.ViewHolder>(){
    inner class ViewHolder(val binding: LayoutAbsensiBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = LayoutAbsensiBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            with(absensiModels[position]){
                binding.tvTanggal.text = this.tanggal
                binding.tvMasuk.text = this.masuk
                binding.tvPulang.text = this.pulang
            }
        }
    }

    override fun getItemCount(): Int {
        return absensiModels.size
    }


}