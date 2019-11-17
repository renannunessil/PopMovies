package br.com.renannunessil.popmovies.extension

import java.text.SimpleDateFormat
import java.util.*

fun String.getCalendarFromStringDate(): Calendar {
    val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
    val date = simpleDateFormat.parse(this)
    val calendar = Calendar.getInstance()

    calendar.time = date

    return calendar
}