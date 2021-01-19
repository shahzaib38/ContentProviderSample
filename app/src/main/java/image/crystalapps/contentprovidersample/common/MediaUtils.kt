package image.crystalapps.contentprovidersample.common

import android.net.Uri
import java.util.concurrent.TimeUnit

object MediaUtils {



    fun getAbsoluteUriPath(imageId :Long) : Uri {
        return   Uri.withAppendedPath(ImageContract.CONTENT_URI, "" + imageId)

    }


    fun durationFormat(duration :String):String{


        return String.format("%d : %d ",
            TimeUnit.MILLISECONDS.toMinutes(duration.toLong()),
            TimeUnit.MILLISECONDS.toSeconds(duration.toLong()) -
                    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration.toLong()))) }

}