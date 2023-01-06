package com.example.sportresults.ui.results

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.sportresults.repository.ResultsRepository
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

    init {
        viewModelScope.launch {
            resultsRepository.refreshResults()
        }
    }

}