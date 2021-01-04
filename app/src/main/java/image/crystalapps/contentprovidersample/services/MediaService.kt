package image.crystalapps.contentprovidersample.services

import com.devbrackets.android.playlistcore.components.playlisthandler.DefaultPlaylistHandler
import com.devbrackets.android.playlistcore.components.playlisthandler.PlaylistHandler
import com.devbrackets.android.playlistcore.service.BasePlaylistService
import image.crystalapps.contentprovidersample.api.AudioApi
import image.crystalapps.contentprovidersample.common.PlaylistManager
import image.crystalapps.contentprovidersample.data.GalleryApp
import image.crystalapps.contentprovidersample.entities.MediaItem


class MediaService : BasePlaylistService<MediaItem, PlaylistManager>() {

    override val playlistManager by lazy { (applicationContext as GalleryApp).playlistManager }

    override fun onCreate() {
        super.onCreate()

        // Adds the audio player implementation, otherwise there's nothing to play media with
        playlistManager.mediaPlayers.add(AudioApi(applicationContext))
    }

    override fun onDestroy() {
        super.onDestroy()

        // Releases and clears all the MediaPlayersMediaImageProvider
        playlistManager.mediaPlayers.forEach {
            it.release()

        }

        playlistManager.mediaPlayers.clear()
    }

    override fun newPlaylistHandler(): PlaylistHandler<MediaItem> {
        val imageProvider = MediaImageProvider(applicationContext) {
            playlistHandler.updateMediaControls()
        }

        return DefaultPlaylistHandler.Builder(
            applicationContext,
            javaClass,
            playlistManager,
            imageProvider
        ).build()
    }
}