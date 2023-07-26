package com.sourcey.materiallogindemo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sourcey.materiallogindemo.databinding.LayoutTabunganBinding
import com.sourcey.materiallogindemo.models.TabunganModels

class AdapterTabungan(var tabunganModels: List<TabunganModels>) :
    RecyclerView.Adapter<AdapterTabungan.ViewHolder>() {
    inner class ViewHolder(val binding: LayoutTabunganBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            LayoutTabunganBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(tabunganModels[position]) {
                binding.tvTanggal.text = this.tanggal
                binding.tvDebit.text = this.debit
                binding.tvKredit.text = this.kredit
                binding.tvSaldo.text = this.kredit

            }
        }
    }

    override fun getItemCount(): Int {
        return tabunganModels.size
    }
}