package com.kh.sbilyhour.composestructure.core.utils

import android.content.Context
import android.net.ConnectivityManager
import javax.inject.Inject

class NetworkUtils @Inject constructor(private val context: Context) {
    fun isConnected(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnected
    }
}