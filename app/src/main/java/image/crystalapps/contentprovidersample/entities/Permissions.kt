package image.crystalapps.contentprovidersample.entities

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.core.content.ContextCompat
import image.crystalapps.contentprovidersample.ui.mainactivity.fragments.photo.PhotoFragment

object Permissions {




//Check Permission
     fun hasPermissions(context: Context?, permissions: Array<String?>?): Boolean {
        if (context != null && permissions != null) {
            for (permission in permissions) {
                if (ContextCompat.checkSelfPermission(
                        context,
                        permission!!
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    return false
                }
            }
        }
        return true
    }








}