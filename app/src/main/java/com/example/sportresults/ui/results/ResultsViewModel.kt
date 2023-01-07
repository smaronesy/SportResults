package com.example.sportresults.ui.results

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.sportresults.repository.ResultsRepository
import com.example.sportresults.utils.convertLongToTime
import com.example.sportresults.utils.getDateOnly
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ResultsViewModel(application: Application, private val resultsRepository: ResultsRepository) : AndroidViewModel(application) {

    private var viewModelJob = Job()

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    val status = resultsRepository._status
    val foneResultsLatest = resultsRepository.foneResultsLatest
    val nbaResultsLatest = resultsRepository.nbaResultsLatest
    val tennisResultsLatest = resultsRepository.tennisResultsLatest
    val latestResults = MutableLiveData<MutableSet<String>>(mutableSetOf())
    val mostRecentDate = MutableLiveData<String>()
//    val latestResults = MutableLiveData<MutableMap<Long, MutableSet<String>>>()

    init {
        viewModelScope.launch {
            resultsRepository.refreshResults()
        }
    }

    fun resultsSetHelper(
        publicationDate: Long,
        resultList: MutableMap<Long, LinkedHashSet<Pair<Long, String>>>,
        foneLatest: String
    ) {
        val dateOnly = getDateOnly(publicationDate)
        if (resultList.contains(dateOnly)) {
            resultList[dateOnly]!!.add(Pair(publicationDate, foneLatest))
        } else {
            resultList[dateOnly] = linkedSetOf(Pair(publicationDate, foneLatest))
        }

        // includes only the results from the most recent date
        val latest = resultList.keys.max()
        mostRecentDate.value = convertLongToTime(latest)

        // sort results by date
        val resultsSortedByDate = sortByDate(resultList[latest]!!)
        latestResults.value = resultsSortedByDate
    }

    private fun sortByDate(set: LinkedHashSet<Pair<Long, String>>): LinkedHashSet<String>{
        val sorted = set.sortedByDescending { it.first }
        val sortedResult = linkedSetOf<String>()
        for(r in sorted){
            sortedResult.add(r.second)
        }
        return sortedResult
    }

}