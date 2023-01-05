package com.example.sportresults.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Results(
    @Json(name = "f1Results")
    val fOne: List<Fone>? = null,
    @Json(name = "nbaResults")
    val nba: List<Nba>? = null,
    @Json(name = "Tennis")
    val tennis: List<Tennis>? = null)

//@JsonClass(generateAdapter = true)
//data class Fone (
//    @Json(name = "publicationDate")
//    val publicationDate: String,
//    @Json(name = "seconds")
//    val seconds: Double,
//    @Json(name = "tournament")
//    val tournament: String,
//    @Json(name = "winner")
//    val winner: String
//    )
//
//@JsonClass(generateAdapter = true)
//data class Nba (
//    @Json(name = "gameNumber")
//    val gameNumber: Int,
//    @Json(name = "loser")
//    val looser: String,
//    @Json(name = "mvp")
//    val mvp: String,
//    @Json(name = "publicationDate")
//    val publicationDate: String,
//    @Json(name = "tournament")
//    val tournament: String,
//    @Json(name = "winner")
//    val winner: String
//    )
//
//@JsonClass(generateAdapter = true)
//data class Tennis (
//    @Json(name = "loser")
//    val looser: String,
//    @Json(name = "numberOfSets")
//    val numberOfSets: Int,
//    @Json(name = "publicationDate")
//    val publicationDate: String,
//    @Json(name = "tournament")
//    val tournament: String,
//    @Json(name = "winner")
//    val winner: String
//    )