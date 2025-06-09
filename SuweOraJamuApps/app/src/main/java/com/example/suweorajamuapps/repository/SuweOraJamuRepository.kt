package com.example.suweorajamuapps.repository

import com.example.suweorajamuapps.data.dao.BarangDao
import com.example.suweorajamuapps.data.dao.TransaksiDao
import com.example.suweorajamuapps.data.model.Barang
import com.example.suweorajamuapps.data.model.Transaksi
import com.example.suweorajamuapps.data.model.TransaksiBarang
import javax.inject.Inject

class SuweOraJamuRepository @Inject constructor(
    private val barangDao: BarangDao,
    private val transaksiDao: TransaksiDao
) {

    suspend fun insertBarang(barang: Barang) {
        barangDao.insert(barang)
    }

    suspend fun updateBarang(barang: Barang) {
        barangDao.updateBarang(barang)
    }

    suspend fun deleteBarang(barang: Barang) {
        barangDao.deleteBarang(barang)
    }

    suspend fun getAllBarang(): List<Barang> = barangDao.getAllBarang()

    suspend fun insertTransaksi(transaksi: Transaksi) {
        return transaksiDao.insertTransaksi(transaksi)
    }

    suspend fun updateTransaksi(transaksi: Transaksi) {
        return transaksiDao.updateTransaksi(transaksi)
    }

    suspend fun deleteTransaksi(transaksi: Transaksi) {
        return transaksiDao.deleteTransaksi(transaksi)
    }

    suspend fun getAllTransaksiBarang(): List<TransaksiBarang> = transaksiDao.getAllTransaksiWithBarang()

}