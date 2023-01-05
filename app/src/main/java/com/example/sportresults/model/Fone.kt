package com.example.sportresults.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Fone(
    val publicationDate: Long,
    val seconds: Double,
    val tournament: String,
    val winner: String
) : Parcelable
