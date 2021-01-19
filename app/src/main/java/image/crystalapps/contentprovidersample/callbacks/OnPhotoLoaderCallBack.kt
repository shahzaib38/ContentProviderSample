package image.crystalapps.contentprovidersample.callbacks

import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import androidx.loader.content.Loader
import image.crystalapps.contentprovidersample.common.ImageContract
import image.crystalapps.contentprovidersample.data.contentprovider.HistoryContract
import image.crystalapps.contentprovidersample.entities.Image
import image.crystalapps.contentprovidersample.extensions.retrieveImages


abstract class OnPhotoLoaderCallBack :BaseLoaderCallBack<List<Image>>() {


    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor) {
      println("Load Finished ${Thread.currentThread().name}")
        onResult(data.retrieveImages()) }

    override fun resetLoader(loader: Loader<Cursor>) {


    }
//
//    override fun getSelections(): String? {
//
//
//        return filterData(true)
//    }
//
//    private fun filterData(filter :Boolean) :String? =  when(true) {
//        true -> {
//            ImageContract.DISLAY_NAME + " like ?" }
//        else->{ null } }
//
//
//    override fun getSelectionsArgs(filter: String): Array<String?>? {
//        return arrayOf("%$filter%") }




    override fun getSortOrderSql(): String? {
        return ImageContract.DATE + " DESC"
    }

    override fun getQueryUri(): Uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI

    override fun getSelectProjection(): Array<String> = ImageContract.getListOfImages()


}