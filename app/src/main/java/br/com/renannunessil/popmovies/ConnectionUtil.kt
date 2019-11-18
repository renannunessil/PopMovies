package br.com.renannunessil.popmovies

import android.content.Context
import android.net.ConnectivityManager


class ConnectionUtil(private val context: Context) {

    fun isConnected(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = connectivityManager.activeNetworkInfo
        return netInfo != null && netInfo.isConnected
    }
}