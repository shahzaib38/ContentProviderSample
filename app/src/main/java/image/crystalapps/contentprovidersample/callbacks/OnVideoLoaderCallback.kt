package image.crystalapps.contentprovidersample.callbacks

import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import androidx.loader.content.Loader
import image.crystalapps.contentprovidersample.common.ImageContract
import image.crystalapps.contentprovidersample.common.VideoContract
import image.crystalapps.contentprovidersample.entities.Video
import image.crystalapps.contentprovidersample.extensions.retrieveVideoData

abstract class OnVideoLoaderCallback : BaseLoaderCallBack<List<Video>>() {


    override fun onResult(result: List<Video>) {

    }

    override fun onReset(loader: Loader<Cursor>) {

    }

    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor) {
       onResult(data.retrieveVideoData())
    }

    override fun resetLoader(loader: Loader<Cursor>) {

    }


    override fun getSortOrderSql(): String? {
        return VideoContract.DATE + " DESC"
    }

    override fun getSelectProjection(): Array<String> {
      return  arrayOf(VideoContract.DISLAY_NAME, VideoContract._ID
      ,VideoContract.DATE
       , VideoContract.SIZE,
        VideoContract.WIDTH,
        ImageContract.HEIGHT,
        VideoContract.PATH)
    }

    override fun getQueryUri(): Uri =MediaStore.Video.Media.EXTERNAL_CONTENT_URI

}