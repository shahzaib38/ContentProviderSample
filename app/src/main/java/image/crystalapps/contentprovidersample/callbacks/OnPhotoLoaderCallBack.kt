package image.crystalapps.contentprovidersample.callbacks

import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import androidx.loader.content.Loader
import image.crystalapps.contentprovidersample.common.ImageContract
import image.crystalapps.contentprovidersample.entities.Image
import image.crystalapps.contentprovidersample.extensions.retrieveAllData
import image.crystalapps.contentprovidersample.extensions.retrieveImages


abstract class OnPhotoLoaderCallBack :BaseLoaderCallBack<List<Image>>() {


    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor) {
      println("Load Finished ${Thread.currentThread().name}")
        onResult(data.retrieveImages()) }

    override fun resetLoader(loader: Loader<Cursor>) {


    }



    override fun getQueryUri(): Uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI


    override fun getSelectProjection(): Array<String> =
        arrayOf(ImageContract.DISLAY_NAME
            , ImageContract._ID
         ,ImageContract.DATE
        ,ImageContract.WIDTH,
            ImageContract.SIZE
        ,ImageContract.HEIGHT
        ,ImageContract.PATH
        )


}