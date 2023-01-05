package com.example.sportresults.ui.results

import android.app.Application
import androidx.lifecycle.AndroidViewModel
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

    val foneLatestResult = resultsRepository.foneResultsLatest
    val nbaLatestResult = resultsRepository.nbaResultsLatest
    val tennisLatestResult = resultsRepository.tennisResultsLatest

    init {
        viewModelScope.launch {
            resultsRepository.refreshResults()
        }
    }

}