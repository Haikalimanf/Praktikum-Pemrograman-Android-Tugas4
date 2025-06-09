package com.example.suweorajamuapps.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.suweorajamuapps.data.model.TransaksiBarang
import com.example.suweorajamuapps.databinding.ItemTransaksiBinding
import java.text.NumberFormat
import java.util.Locale

class TransaksiAdapter(
    private val onItemClick: (TransaksiBarang) -> Unit
): ListAdapter<TransaksiBarang, TransaksiAdapter.ViewHolder>(DiffCallback()) {
    class ViewHolder(private val binding: ItemTransaksiBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(transaksi: TransaksiBarang, onItemClick: (TransaksiBarang) -> Unit){

            val totalBayar = transaksi.barang.harga * transaksi.transaksi.jumlah

            binding.textNamaBarang.text = transaksi.barang.nama
            binding.textHargaBarang.text = "Harga : ${transaksi.barang.harga}"
            binding.textjumlah.text = "Jumlah : ${transaksi.transaksi.jumlah}"
            binding.textTanggal.text = "Tanggal : ${transaksi.transaksi.tanggal}"

            val formatedTotalBayar = NumberFormat
                .getCurrencyInstance(Locale("id", "ID"))
                .format(totalBayar)

            binding.textTotal.text = "Total : $formatedTotalBayar"

            Glide.with(binding.root.context)
                .load(transaksi.barang.imageUri)
                .into(binding.imageBarang)

            binding.root.setOnClickListener {
                onItemClick(transaksi)
            }

        }
    }

    fun getTotalBiaya(): Double {
        return currentList.sumOf {
            it.barang.harga * it.transaksi.jumlah
        }
    }

    class DiffCallback: DiffUtil.ItemCallback<TransaksiBarang>() {
        override fun areItemsTheSame(oldItem: TransaksiBarang, newItem: TransaksiBarang): Boolean {
            return oldItem.transaksi.id == newItem.transaksi.id
        }

        override fun areContentsTheSame(
            oldItem: TransaksiBarang,
            newItem: TransaksiBarang,
        ): Boolean {
            return oldItem.transaksi.id == newItem.transaksi.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemTransaksiBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), onItemClick)
    }
}