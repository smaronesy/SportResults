package com.example.sportresults.utils

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.sportresults.R
import com.example.sportresults.repository.ResultsApiStatus

@BindingAdapter("sport")
fun bindSportImage(imageView: ImageView, res: String) {
    if(res.contains("sets")){
        imageView.setImageResource(R.drawable.ic_sports_tennis)
    } else if(res.contains("seconds")) {
        imageView.setImageResource(R.drawable.ic_sports_f1)
    } else {
        imageView.setImageResource(R.drawable.ic_sports_basketball)
    }
}

@BindingAdapter("result")
fun bindSportResult(textView: TextView, res: String){
    textView.text = res
}

@BindingAdapter("resultsApiStatus")
fun bindStatus(proBar: ProgressBar, status: ResultsApiStatus?){
    when(status) {
        ResultsApiStatus.LOADING -> {
            proBar.visibility = View.VISIBLE
        }
        ResultsApiStatus.DONE -> {
            proBar.visibility = View.GONE
        }
        ResultsApiStatus.ERROR -> {
            proBar.visibility = View.GONE
        }
        else -> {}
    }
}

@BindingAdapter("connectionStatus")
fun bindConnectionStatus(imageView: ImageView, status: ResultsApiStatus?){
    when(status) {
        ResultsApiStatus.ERROR -> {
            imageView.visibility = View.VISIBLE
            imageView.setImageResource(R.drawable.ic_no_connection)
        }
        else -> {
            imageView.visibility = View.INVISIBLE
            imageView.setImageResource(0)
        }
    }
}