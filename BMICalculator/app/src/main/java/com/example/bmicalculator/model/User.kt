package com.example.bmicalculator.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val bmi: Double,
) :Parcelable