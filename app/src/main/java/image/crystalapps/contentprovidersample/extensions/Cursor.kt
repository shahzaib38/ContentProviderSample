package image.crystalapps.contentprovidersample.extensions

import android.database.Cursor
import image.crystalapps.contentprovidersample.common.ImageContract
import image.crystalapps.contentprovidersample.entities.Image


fun Cursor.retrieveImages() :List<Image> {
    val list = ArrayList<Image>()


    val columnIndexID = this.getColumnIndexOrThrow(ImageContract._ID)
    val columnIndexDisplayName           =   this.getColumnIndex(ImageContract.DISLAY_NAME)
    val columnIndexDate           =   this.getColumnIndex(ImageContract.DATE)
    val columnIndexSize           =   this.getColumnIndex(ImageContract.SIZE)
    val columnIndexWidth           =   this.getColumnIndex(ImageContract.WIDTH)
    val columnIndexHeight           =   this.getColumnIndex(ImageContract.HEIGHT)
    val path           =   this.getColumnIndex(ImageContract.PATH)



    while (this.moveToNext()) {
        val imageId = this.getLong(columnIndexID)
        val uriImage = ImageContract.getAbsoluteUriPath(imageId)
        val displayName        =this.getString(columnIndexDisplayName)
        val date =this.getString(columnIndexDate)
        val size =this.getString(columnIndexSize)
        val width =this.getString(columnIndexWidth)
        val height =this.getString(columnIndexHeight)
        val path =this.getString(path)



        list.add(Image(imageId,uriImage.toString(),displayName,date,size,width,height,path)) }
    return list }


fun Cursor.retrieveAllData() :List<String> {
    val list = ArrayList<String>()


    val columnIndexID = this.getColumnIndexOrThrow(ImageContract._ID)
    val columnIndexDisplayName           =   this.getColumnIndex(ImageContract.DISLAY_NAME)
    val columnIndexDate           =   this.getColumnIndex(ImageContract.DATE)
    val columnIndexSize           =   this.getColumnIndex(ImageContract.SIZE)
    val columnIndexWidth           =   this.getColumnIndex(ImageContract.WIDTH)
    val columnIndexHeight           =   this.getColumnIndex(ImageContract.HEIGHT)
    val path           =   this.getColumnIndex(ImageContract.PATH)



    while (this.moveToNext()) {
        val imageId = this.getLong(columnIndexID)
        val uriImage = ImageContract.getAbsoluteUriPath(imageId)
        val displayName        =this.getString(columnIndexDisplayName)
        val date =this.getString(columnIndexDate)
        val size =this.getString(columnIndexSize)
        val width =this.getString(columnIndexWidth)
        val height =this.getString(columnIndexHeight)
        val path =this.getString(path)

//        image.id=imageId
//        image.uriImage=uriImage
//        image.displayName=displayName
//        image.date=date
//        image.size=size
//        image.width=width
//        image.height=height
//        image.path=path


        list.add(uriImage.toString()) }
    return list }

