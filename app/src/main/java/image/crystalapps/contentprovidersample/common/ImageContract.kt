package image.crystalapps.contentprovidersample.common

import android.net.Uri
import android.provider.MediaStore

object ImageContract {

    val _ID=MediaStore.Images.Media._ID
    val CONTENT_URI = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
    val DISLAY_NAME = MediaStore.Images.Media.DISPLAY_NAME
    val DATE =MediaStore.Images.Media.DATE_TAKEN
    val SIZE= MediaStore.Images.Media.SIZE
    val WIDTH=MediaStore.Images.Media.WIDTH
    val HEIGHT =MediaStore.Images.Media.HEIGHT
    val PATH =MediaStore.Images.Media.BUCKET_DISPLAY_NAME


fun getAbsoluteUriPath(imageId :Long) :Uri{
 return   Uri.withAppendedPath(CONTENT_URI, "" + imageId)

}
}