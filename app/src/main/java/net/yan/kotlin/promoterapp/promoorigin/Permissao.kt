@file:Suppress("DEPRECATED_IDENTITY_EQUALS")

package net.yan.kotlin.promoterapp.promoorigin


import android.Manifest
import android.app.Activity
import android.os.Build
import androidx.core.app.ActivityCompat

object Permissao {
    fun validarPermissoes(
        activity: Activity
    ): Boolean {
        if (Build.VERSION.SDK_INT >= 23) {
            ActivityCompat.requestPermissions(
                activity,
                arrayOf(
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.ACCESS_NETWORK_STATE,
                    Manifest.permission.CAMERA
                ),
                1
            )
        }
        return true
    }
}