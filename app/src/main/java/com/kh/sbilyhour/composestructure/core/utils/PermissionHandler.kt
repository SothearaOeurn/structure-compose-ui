package com.kh.sbilyhour.composestructure.core.utils

import android.content.Context
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.result.ActivityResultCaller
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import javax.inject.Inject

class PermissionHandler @Inject constructor(
    private val context: Context
) {

    // Handle the permission request using ActivityResultCaller
    fun launchPermissionsRequest(
        activityResultCaller: ActivityResultCaller,
        permissions: Array<String>
    ) {
        val requestPermissionLauncher =
            activityResultCaller.registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissionsResult ->
                val deniedPermissions = permissionsResult.filter { it.value == false }

                if (deniedPermissions.isNotEmpty()) {
                    showToast("Permissions Denied: ${deniedPermissions.keys.joinToString(", ")}")
                } else {
                    showToast("All Permissions Granted")
                }
            }

        requestPermissionLauncher.launch(permissions)
    }

    // Check if a specific permission is granted
    fun isPermissionGranted(permission: String): Boolean {
        return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED
    }

    private fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}




