package com.example.sportresults.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Nba(
    val gameNumber: Int,
    val looser: String,
    val mvp: String,
    val publicationDate: Long,
    val tournament: String,
    val winner: String
) : Parcelable
