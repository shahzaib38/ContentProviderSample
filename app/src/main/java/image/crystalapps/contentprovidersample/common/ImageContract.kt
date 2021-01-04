package image.crystalapps.contentprovidersample.common

import android.net.Uri
import android.provider.MediaStore

object ImageContract {

     const val _ID=MediaStore.Images.Media._ID
     val CONTENT_URI = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
     const val DISLAY_NAME = MediaStore.Images.Media.DISPLAY_NAME
     const  val DATE =MediaStore.Images.Media.DATE_TAKEN
     const  val SIZE= MediaStore.Images.Media.SIZE
     const val WIDTH=MediaStore.Images.Media.WIDTH
     const val HEIGHT =MediaStore.Images.Media.HEIGHT
     const val PATH =MediaStore.Images.Media.BUCKET_DISPLAY_NAME



fun getAbsoluteUriPath(imageId :Long) :Uri{
 return   Uri.withAppendedPath(CONTENT_URI, "" + imageId)

}
}