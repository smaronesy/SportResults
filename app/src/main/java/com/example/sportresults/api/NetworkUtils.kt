package com.example.sportresults.api

import com.example.sportresults.model.Fone
import com.example.sportresults.model.Nba
import com.example.sportresults.model.Tennis
import org.json.JSONArray
import org.json.JSONObject
import java.text.SimpleDateFormat

fun parseFoneResults(jsonResult: JSONObject): List<Fone> {

    val foneResults = mutableListOf<Fone>()

    val foneArray = jsonResult.get("f1Results")
    for(i in 0 until (foneArray as JSONArray).length()){

        val result = foneArray.getJSONObject(i)

        val dateString = result.get("publicationDate") as String
        val formatter = SimpleDateFormat("MMMM d, yyyy hh:mm:ss a")
        val publicationDate: Long = formatter.parse(dateString).time

        val seconds = result.get("seconds") as Double
        val tournament = result.get("tournament") as String
        val winner = result.get("winner") as String
        foneResults.add(Fone(publicationDate, seconds, tournament, winner))
    }

    return foneResults
}

fun parseNbaResults(jsonResult: JSONObject): List<Nba> {

    val nbaResults = mutableListOf<Nba>()

    val nbaArray = jsonResult.get("nbaResults")
    for(i in 0 until (nbaArray as JSONArray).length()){

        val result = nbaArray.getJSONObject(i)

        val gameNumber = result.get("gameNumber") as Int
        val looser = result.get("looser") as String
        val mvp = result.get("mvp") as String

        val dateString = result.get("publicationDate") as String
        val formatter = SimpleDateFormat("MMMM d, yyyy hh:mm:ss a")
        val publicationDate: Long = formatter.parse(dateString).time

        val tournament = result.get("tournament") as String
        val winner = result.get("winner") as String

        nbaResults.add(Nba(gameNumber, looser, mvp, publicationDate, tournament, winner))
    }

    return nbaResults
}

fun parseTennisResults(jsonResult: JSONObject): List<Tennis> {

    val tennisResults = mutableListOf<Tennis>()

    val tennisArray = jsonResult.get("Tennis")
    for(i in 0 until (tennisArray as JSONArray).length()){

        val result = tennisArray.getJSONObject(i)

        val looser = result.get("looser") as String
        val numberOfSets = result.get("numberOfSets") as Int

        val dateString = result.get("publicationDate") as String
        val formatter = SimpleDateFormat("MMMM d, yyyy hh:mm:ss a")
        val publicationDate: Long = formatter.parse(dateString).time

        val tournament = result.get("tournament") as String
        val winner = result.get("winner") as String

        tennisResults.add(Tennis(looser, numberOfSets, publicationDate, tournament, winner))
    }

    return tennisResults
}
