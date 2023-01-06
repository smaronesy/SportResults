package com.example.sportresults.utils

import java.text.SimpleDateFormat
import java.util.*

fun convertLongToTime(time: Long): String {
    val date = Date(time)
    val format = SimpleDateFormat("yyyy.MM.dd")
    return format.format(date)
}

fun currentTimeToLong(): Long {
    return System.currentTimeMillis()
}

fun convertDateToLong(date: String): Long {
    val df = SimpleDateFormat("yyyy.MM.dd")
    return df.parse(date).time
}

fun getDateOnly(time: Long): Long {
    val dateOnly = convertLongToTime(time)
    val dateOnlyLong = convertDateToLong(dateOnly)
    return dateOnlyLong
}