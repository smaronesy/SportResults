package com.example.sportresults.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Tennis(
    val looser: String,
    val numberOfSets: Int,
    val publicationDate: Long,
    val tournament: String,
    val winner: String
) : Parcelable
