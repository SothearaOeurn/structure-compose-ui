package com.kh.sbilyhour.composestructure.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Handler
import android.os.Looper
import kotlin.random.Random

class NetworkSpeedMonitor(private val context: Context) {

    enum class SpeedCategory {
        SLOW, MEDIUM, FAST, NO_CONNECTION
    }

    interface SpeedMonitorListener {
        fun onSpeedChanged(speedCategory: SpeedCategory, downloadSpeedKbps: Int, uploadSpeedKbps: Int)
    }

    private var speedMonitorListener: SpeedMonitorListener? = null
    private val handler = Handler(Looper.getMainLooper())
    private val updateInterval: Long = 1000 // 1 second for live updates

    private var lastRxBytes = 0L
    private var lastTxBytes = 0L

    fun setSpeedMonitorListener(listener: SpeedMonitorListener) {
        this.speedMonitorListener = listener
    }

    fun startMonitoring() {
        handler.postDelayed(object : Runnable {
            override fun run() {
                val networkCapabilities = getActiveNetworkCapabilities()
                if (networkCapabilities != null) {
                    val currentRxBytes = getNetworkRxBytes()
                    val currentTxBytes = getNetworkTxBytes()

                    val downloadSpeedKbps = ((currentRxBytes - lastRxBytes) * 8 / 1024).toInt()
                    val uploadSpeedKbps = ((currentTxBytes - lastTxBytes) * 8 / 1024).toInt()

                    lastRxBytes = currentRxBytes
                    lastTxBytes = currentTxBytes

                    val speedCategory = determineSpeedCategory(downloadSpeedKbps)

                    speedMonitorListener?.onSpeedChanged(speedCategory, downloadSpeedKbps, uploadSpeedKbps)
                } else {
                    speedMonitorListener?.onSpeedChanged(SpeedCategory.NO_CONNECTION, 0, 0)
                }

                // Repeat the task
                handler.postDelayed(this, updateInterval)
            }
        }, updateInterval)
    }

    fun stopMonitoring() {
        handler.removeCallbacksAndMessages(null)
    }

    private fun getActiveNetworkCapabilities(): NetworkCapabilities? {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork
        return connectivityManager.getNetworkCapabilities(activeNetwork)
    }

    private fun getNetworkRxBytes(): Long {
        // Replace with actual network Rx bytes tracking (placeholder logic for demo)
        return Random.nextLong(1_000, 1_000_000) // Simulate random traffic
    }

    private fun getNetworkTxBytes(): Long {
        // Replace with actual network Tx bytes tracking (placeholder logic for demo)
        return Random.nextLong(500, 500_000) // Simulate random traffic
    }

    private fun determineSpeedCategory(downloadSpeedKbps: Int): SpeedCategory {
        return when {
            downloadSpeedKbps < 1024 -> SpeedCategory.SLOW
            downloadSpeedKbps in 1024..5120 -> SpeedCategory.MEDIUM
            downloadSpeedKbps > 5120 -> SpeedCategory.FAST
            else -> SpeedCategory.NO_CONNECTION
        }
    }
}