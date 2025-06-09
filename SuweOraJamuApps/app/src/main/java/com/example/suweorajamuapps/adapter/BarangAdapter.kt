package com.example.suweorajamuapps.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.suweorajamuapps.data.model.Barang
import com.example.suweorajamuapps.databinding.ItemBarangBinding

class BarangAdapter(private val onItemClick: (Barang) -> Unit
    ): ListAdapter<Barang, BarangAdapter.ViewHolder>(DiffCallback()) {

    class ViewHolder(private val binding: ItemBarangBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(barang: Barang, onItemClick: (Barang) -> Unit) {
            binding.textNamaBarang.text = barang.nama

            val formatedHarga = java.text.NumberFormat
                .getCurrencyInstance(java.util.Locale("id", "ID"))
                .format(barang.harga)

            binding.textHargaBarang.text = "Harga : $formatedHarga"

            Glide.with(binding.root.context)
                .load(Uri.parse(barang.imageUri))
                .into(binding.imageBarang)

            binding.root.setOnClickListener {
                onItemClick(barang)
            }
        }
    }

    class DiffCallback: DiffUtil.ItemCallback<Barang>() {
        override fun areItemsTheSame(oldItem: Barang, newItem: Barang): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Barang, newItem: Barang): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemBarangBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), onItemClick)
    }
}