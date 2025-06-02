package com.example.fromfgdmvvmprojects.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AttendanceModel(
    val nama: String = "",
    val telefon: String = "",
    val email: String = "",
    val gender: String = "",
    val skillset: List<String> = emptyList(),
    val categori: String = ""
) : Parcelable
