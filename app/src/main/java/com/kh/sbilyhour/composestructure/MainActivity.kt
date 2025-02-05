package com.kh.sbilyhour.composestructure

import AppNavHost
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.ExperimentalAnimationApi
import com.google.accompanist.navigation.animation.rememberAnimatedNavController


import com.kh.sbilyhour.composestructure.presentation.ui.theme.ComposeStructureTheme
import com.kh.sbilyhour.composestructure.core.utils.NetworkSpeedMonitor
import com.kh.sbilyhour.composestructure.data.datasource.local.datastore.UserPreferencesDataSource
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity(), NetworkSpeedMonitor.SpeedMonitorListener {
    private lateinit var networkMonitor: NetworkSpeedMonitor
    @Inject
    lateinit var userPreferencesDataSource: UserPreferencesDataSource

    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        networkMonitor = NetworkSpeedMonitor(this)
        networkMonitor.setSpeedMonitorListener(this)
        networkMonitor.startMonitoring()
        setContent {
            ComposeStructureTheme {
                val navController = rememberAnimatedNavController()
                AppNavHost(navController = navController, userPreferencesDataSource = userPreferencesDataSource)
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



