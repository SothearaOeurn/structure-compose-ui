package com.kh.sbilyhour.composestructure

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge


import com.kh.sbilyhour.composestructure.presentation.ui.navigation.AppNavHost
import com.kh.sbilyhour.composestructure.presentation.ui.theme.ComposeStructureTheme
import com.kh.sbilyhour.composestructure.core.utils.NetworkSpeedMonitor
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity(), NetworkSpeedMonitor.SpeedMonitorListener {
    private lateinit var networkMonitor: NetworkSpeedMonitor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        networkMonitor = NetworkSpeedMonitor(this)
        networkMonitor.setSpeedMonitorListener(this)
        networkMonitor.startMonitoring()
        setContent {
            ComposeStructureTheme {
                AppNavHost()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        networkMonitor.startMonitoring()
    }

    override fun onPause() {
        super.onPause()
        networkMonitor.stopMonitoring()
    }

    override fun onDestroy() {
        super.onDestroy()
        networkMonitor.stopMonitoring()
    }

    override fun onSpeedChanged(
        speedCategory: NetworkSpeedMonitor.SpeedCategory,
        downloadSpeedKbps: Int,
        uploadSpeedKbps: Int
    ) {
        when (speedCategory) {
            NetworkSpeedMonitor.SpeedCategory.SLOW -> {
                println("Speed: Slow (Download: $downloadSpeedKbps Kbps, Upload: $uploadSpeedKbps Kbps)")
            }

            NetworkSpeedMonitor.SpeedCategory.MEDIUM -> {
                println("Speed: Medium (Download: $downloadSpeedKbps Kbps, Upload: $uploadSpeedKbps Kbps)")
            }

            NetworkSpeedMonitor.SpeedCategory.FAST -> {
                println("Speed: Fast (Download: $downloadSpeedKbps Kbps, Upload: $uploadSpeedKbps Kbps)")
            }

            NetworkSpeedMonitor.SpeedCategory.NO_CONNECTION -> {
                println("No Internet Connection")
            }
        }
    }
}



