package com.example.sportresults.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.sportresults.api.ResultsApi
import com.example.sportresults.database.ResultsDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import com.example.sportresults.api.*
import com.example.sportresults.database.tables.*

class ResultsRepository(private val resultsDao: ResultsDao,
                        private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO) {


    val foneResultsLatest: LiveData<FoneEntity> = resultsDao.getLatestFoneResult()
    val nbaResultsLatest: LiveData<NbaEntity> = resultsDao.getLatestNbaResult()
    val tennisResultsLatest: LiveData<TennisEntity> = resultsDao.getLatestTennisResult()

    // method to refresh offline cash
    suspend fun refreshResults() {
        withContext(Dispatchers.IO) {
            try {
                val response = ResultsApi.retrofitService.getResults()

                val jsonBody = JSONObject(response)

                val foneResults = parseFoneResults(jsonBody)
                resultsDao.insertAllFoneResults(*foneResults.asDatabaseObject())

                val nbaResults = parseNbaResults(jsonBody)
                resultsDao.insertAllNbaResults(*nbaResults.asDatabaseObject())

                val tennisResults = parseTennisResults(jsonBody)
                resultsDao.insertAllTennisResults(*tennisResults.asDatabaseObject())

            } catch (e: Exception) {
                Log.e("APICALL ERROR", e.message.toString())
            }

        }
    }
}