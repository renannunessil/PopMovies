package br.com.renannunessil.popmovies.extension

import java.text.SimpleDateFormat
import java.util.*

fun Calendar.brDateFormat() : String {
    val brFormat = "dd/MM/yyyy"
    val pattern = SimpleDateFormat(brFormat)
    return pattern.format(this.time)
}