package com.example.sportresults.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.sportresults.R

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