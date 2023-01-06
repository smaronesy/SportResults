package com.example.sportresults.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.sportresults.api.ResultsApi
import com.example.sportresults.database.ResultsDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import com.example.sportresults.api.*
import com.example.sportresults.database.tables.*

enum class ResultsApiStatus {LOADING, ERROR, DONE}

class ResultsRepository(private val resultsDao: ResultsDao,
                        private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO) {

    val _status = MutableLiveData<ResultsApiStatus>()

    val foneResultsLatest: LiveData<FoneEntity> = resultsDao.getLatestFoneResult()
    val nbaResultsLatest: LiveData<NbaEntity> = resultsDao.getLatestNbaResult()
    val tennisResultsLatest: LiveData<TennisEntity> = resultsDao.getLatestTennisResult()

    // method to refresh offline cash
    suspend fun refreshResults() {
        withContext(Dispatchers.IO) {
            try {
                _status.postValue(ResultsApiStatus.LOADING)
                val response = ResultsApi.retrofitService.getResults()

                val jsonBody = JSONObject(response)

                val foneResults = parseFoneResults(jsonBody)
                resultsDao.insertAllFoneResults(*foneResults.asDatabaseObject())

                val nbaResults = parseNbaResults(jsonBody)
                resultsDao.insertAllNbaResults(*nbaResults.asDatabaseObject())

                val tennisResults = parseTennisResults(jsonBody)
                resultsDao.insertAllTennisResults(*tennisResults.asDatabaseObject())

                _status.postValue(ResultsApiStatus.DONE)

            } catch (e: Exception) {
                _status.postValue(ResultsApiStatus.ERROR)
                Log.e("API CALL ERROR", e.message.toString())
            }
        }
    }
}