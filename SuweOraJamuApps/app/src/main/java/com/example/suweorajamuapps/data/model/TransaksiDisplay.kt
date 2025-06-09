package com.example.suweorajamuapps.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TransaksiDisplay(
    val id: Int,
    val barangId: Int,
    val jumlah: Int,
    val tanggal: String
): Parcelable {

    constructor(tansaksi: Transaksi) : this(
        id = tansaksi.id,
        barangId = tansaksi.barangId,
        jumlah = tansaksi.jumlah,
        tanggal = tansaksi.tanggal
    )

    fun toTransaksi(): Transaksi {
        return Transaksi(
            id = id,
            barangId = barangId,
            jumlah = jumlah,
            tanggal = tanggal
        )
    }

}
