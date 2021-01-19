package image.crystalapps.contentprovidersample.extensions

import android.database.Cursor
import image.crystalapps.contentprovidersample.common.ImageContract
import image.crystalapps.contentprovidersample.common.MediaUtils
import image.crystalapps.contentprovidersample.common.VideoContract
import image.crystalapps.contentprovidersample.entities.Image
import image.crystalapps.contentprovidersample.entities.Video


fun Cursor.retrieveImages() :List<Image> {
    val list = ArrayList<Image>()
    val columnIndexID = this.getColumnIndexOrThrow(ImageContract._ID)
    val columnIndexDisplayName           =   this.getColumnIndex(ImageContract.DISLAY_NAME)
    val columnIndexDate           =   this.getColumnIndex(ImageContract.DATE)
    val columnIndexSize           =   this.getColumnIndex(ImageContract.SIZE)
    val columnIndexWidth           =   this.getColumnIndex(ImageContract.WIDTH)
    val columnIndexHeight           =   this.getColumnIndex(ImageContract.HEIGHT)
    val columnIndexPath           =   this.getColumnIndex(ImageContract.PATH)

    while (this.moveToNext()) {
        val imageId = this.getLong(columnIndexID)
        val uriImage = MediaUtils.getAbsoluteUriPath(imageId)
        val displayName=this.getString(columnIndexDisplayName)
        val date=this.getString(columnIndexDate)
        val size=this.getString(columnIndexSize)
        val width=this.getString(columnIndexWidth)
        val height=this.getString(columnIndexHeight)
        val path=this.getString(columnIndexPath)

        list.add(createImage(imageId,uriImage.toString(),displayName,date,size,width,height,path)) }
    return list
}


fun createImage(imageId :Long,uriImage :String ,
                displayName :String ,
                date :String ,size :String ,width :String ,
                height :String,path:String):Image{
    return Image(imageId,uriImage,displayName,date,size,width,height,path) }


fun Cursor.retrieveVideoData() :List<Video> {
    val list = ArrayList<Video>()

    val columnIndexID = this.getColumnIndexOrThrow(VideoContract._ID)
    val columnIndexDisplayName=this.getColumnIndex(VideoContract.DISLAY_NAME)
    val columnIndexDate           =   this.getColumnIndex(VideoContract.DATE)
    val columnIndexSize           =   this.getColumnIndex(VideoContract.SIZE)
    val columnIndexWidth           =   this.getColumnIndex(VideoContract.WIDTH)
    val columnIndexHeight           =   this.getColumnIndex(VideoContract.HEIGHT)
    val path           =   this.getColumnIndex(VideoContract.PATH)
     val columnsIndexDuration =this.getColumnIndex(VideoContract.DURATION)
    while (this.moveToNext()) {
        val imageId = this.getLong(columnIndexID)
        val uriImage = MediaUtils.getAbsoluteUriPath(imageId)
        val displayName        =this.getString(columnIndexDisplayName)
        val date =this.getString(columnIndexDate)
        val size =this.getString(columnIndexSize)
        val width =this.getString(columnIndexWidth)
        val height =this.getString(columnIndexHeight)
        val path =this.getString(path)
          val duration =this.getString(columnsIndexDuration)

        println("Duration $duration")
        list.add(createVideo(imageId,uriImage.toString(),displayName,date,size,width,height,path ,"" ,duration)) }
    return list }



private fun createVideo(imageId :Long,uriImage :String,displayName :String ,
                        date :String,size :String,width:String ,
                        height:String,path:String ,thumb:String ,duration :String) :Video{
               return  Video(imageId,uriImage,displayName,date,size,width,height,path,thumb ,duration)
}
