package image.crystalapps.contentprovidersample.callbacks

import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import androidx.loader.content.Loader
import image.crystalapps.contentprovidersample.extensions.retrieveAllData

abstract class OnVideoLoaderCallback : BaseLoaderCallBack<List<String>>() {


    override fun onResult(result: List<String>) {

    }

    override fun onReset(loader: Loader<Cursor>) {

    }

    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor) {
       onResult(data.retrieveAllData())
    }

    override fun resetLoader(loader: Loader<Cursor>) {

    }

    override fun getSelectProjection(): Array<String> {
      return  arrayOf(MediaStore.Video.Media.DISPLAY_NAME, MediaStore.Video.VideoColumns.DATA, MediaStore.Video.Media._ID)
    }

    override fun getQueryUri(): Uri =MediaStore.Video.Media.EXTERNAL_CONTENT_URI

}