package image.crystalapps.contentprovidersample.data

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import image.crystalapps.contentprovidersample.common.PlaylistManager

@HiltAndroidApp
class GalleryApp : Application() {

    val playlistManager: PlaylistManager by lazy { PlaylistManager(this) }

    override fun onCreate() {
        super.onCreate()
    }

}