package image.crystalapps.contentprovidersample.common

import android.provider.MediaStore

object VideoContract {

    const  val _ID= MediaStore.Video.Media._ID
    const  val thumbnail =MediaStore.Video.Thumbnails.MINI_KIND
    val CONTENT_URI = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
    const  val DISLAY_NAME = MediaStore.Video.Media.DISPLAY_NAME
    const val DATE = MediaStore.Video.Media.DATE_TAKEN
    const val SIZE= MediaStore.Video.Media.SIZE
    const val WIDTH= MediaStore.Video.Media.WIDTH
    const val HEIGHT = MediaStore.Video.Media.HEIGHT
    const val PATH = MediaStore.Video.Media.BUCKET_DISPLAY_NAME
    const val DURATION = MediaStore.Video.Media.DURATION



    fun getVideoList() :Array<String> = arrayOf(DISLAY_NAME,_ID, DATE, SIZE, WIDTH, HEIGHT, PATH,
        DURATION)



}