package com.sourcey.materiallogindemo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sourcey.materiallogindemo.databinding.LayoutSiswaBinding
import com.sourcey.materiallogindemo.models.SiswaModels

class AdapterSiswa(var context: Context, var siswaModels: List<SiswaModels>) : RecyclerView.Adapter<AdapterSiswa.ViewHolder>() {
    inner class ViewHolder(val binding: LayoutSiswaBinding) : RecyclerView.ViewHolder(binding.root)

    private var onClickDelete: ((SiswaModels) -> Unit)? = null
    private var onClickUpdate: ((SiswaModels) -> Unit)? = null
    private var onClickIntent: ((SiswaModels) -> Unit)? = null

    fun setOnClickDelete(callback: (SiswaModels) -> Unit){
        this.onClickDelete = callback
    }

    fun setOnClickUpdate(callback: (SiswaModels) -> Unit){
        this.onClickUpdate = callback
    }

    fun setOnClickIntent(callback: (SiswaModels) -> Unit){
        this.onClickIntent = callback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = LayoutSiswaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            with(siswaModels[position]){
                binding.tvNama.text = "Nama : "+ this.nama
                binding.tvNis.text = "Nis : " +this.nis

                binding.btnDelete.setOnClickListener {
                    onClickDelete?.invoke(siswaModels[position])
                }

                binding.btnEdit.setOnClickListener {
                    onClickUpdate?.invoke(siswaModels[position])
                }

                binding.cardSiswa.setOnClickListener{
                    onClickIntent?.invoke(siswaModels[position])
                }

            }
        }
    }

    override fun getItemCount(): Int {
        return siswaModels.size
    }


}