package image.crystalapps.contentprovidersample



import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import androidx.lifecycle.*
import androidx.loader.app.LoaderManager
import androidx.loader.content.Loader
import image.crystalapps.contentprovidersample.callbacks.OnPhotoLoaderCallBack
import image.crystalapps.contentprovidersample.callbacks.OnVideoLoaderCallback
import image.crystalapps.contentprovidersample.entities.Video
import image.crystalapps.contentprovidersample.loader.LoaderManagerCallBack
import image.crystalapps.contentprovidersample.ui.mainactivity.fragments.videos.VideosFragment
import kotlinx.coroutines.*

class VideoLoader private constructor(private  val context :Context){


    val videosLiveData = MutableLiveData<List<Video>?>(emptyList())

    lateinit var loaderCursor : Loader<Cursor>
    fun init(owner: VideosFragment){
        val loader = LoaderManagerCallBack(context, photoManager)
        loaderCursor= LoaderManager.getInstance(owner).initLoader(VIDEO_LOADER, null, loader)

    }




    private val photoManager = object : OnVideoLoaderCallback() {
        override fun onResult(result: List<Video>) {

            runBlocking(Dispatchers.Main) {
                setLiveData(result)


            } }


        override fun onReset(loader: Loader<Cursor>) {
            // setLiveData(null)
        }
    }


    fun setLiveData(result :List<Video>?){
        videosLiveData.value=result }


    companion object{
        private const val VIDEO_LOADER = 2

        fun getInstance(context: Context):VideoLoader{
            return VideoLoader(context) }
    }



}