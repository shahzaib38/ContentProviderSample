package image.crystalapps.contentprovidersample.data

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class GalleryApp : Application() {

    override fun onCreate() {
        super.onCreate()
    }

}