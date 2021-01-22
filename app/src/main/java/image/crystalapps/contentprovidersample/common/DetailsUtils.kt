package image.crystalapps.contentprovidersample.common

import image.crystalapps.contentprovidersample.entities.Details
import image.crystalapps.contentprovidersample.entities.Image
import java.text.DecimalFormat
import java.util.concurrent.TimeUnit

object DetailsUtils {

    private const val TITLE = "Title"
    private const val DATE = "Date"
    private const val RESOLUTION = "Resolution"
    private const val SIZE = "Size"
    private const val PATH = "Path"

    fun arrayListOfDetails():Array<String>{
        return arrayOf(TITLE,DATE, RESOLUTION, SIZE, PATH) }



    fun createDetailObject(image: Image?):Details?{
      return  if(image!=null) {
            val title = image.displayName
            val date = image.date
            val resolution = createResolution(image.width, image.height)
           val size = getFileSize(image.size?.toDouble())
          val path = image.path
          Details(title,date,resolution,size,path
          )
        }else{
            null
        }
    }

   private fun createResolution(width  :String? ,height :String?):String {
     return  if (width.isNullOrEmpty() && height.isNullOrEmpty()) {
         "none"
       }else{
         String.format(" X ", height)
     }
   }


    private fun   getFileSize( size:Double?):String {
      return  if (size != null) {
            if (size <= 0){ "0"}


            val  units = arrayOf( "B", "KB", "MB", "GB", "TB" )
            val  digitGroups = (Math.log10(size) / Math.log10(1024.0))
              DecimalFormat("#,##0.#").format(size / Math.pow(1024.0, digitGroups)) + " " + units[digitGroups.toInt()];

        }else{

            "none"
        }

    }
}

