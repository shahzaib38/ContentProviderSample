package image.crystalapps.contentprovidersample

import android.content.Context
import android.database.Cursor
import androidx.lifecycle.*
import androidx.loader.app.LoaderManager
import androidx.loader.content.Loader
import image.crystalapps.contentprovidersample.callbacks.OnPhotoLoaderCallBack
import image.crystalapps.contentprovidersample.entities.Image
import image.crystalapps.contentprovidersample.loader.LoaderManagerCallBack
import image.crystalapps.contentprovidersample.ui.mainactivity.fragments.photo.PhotoFragment
import kotlinx.coroutines.*

class PhotoLoader private constructor(private  val context :Context){

    val photosLiveData = MutableLiveData<List<Image>?>(emptyList())

    lateinit var loaderCursor : Loader<Cursor>
    fun init(owner: PhotoFragment){
        val loader = LoaderManagerCallBack(context, photoManager)
        loaderCursor= LoaderManager.getInstance(owner).initLoader(IMAGES_LOADER, null, loader)

    }


    fun reset(){


        if(loaderCursor!=null) {
            if (!loaderCursor.isReset) {
                loaderCursor.reset()
            }
        }

    }


    private val photoManager = object : OnPhotoLoaderCallBack() {
        override fun onResult(result: List<Image>) {
            runBlocking(Dispatchers.Main) {
                setLiveData(result)

            } }


        override fun onReset(loader: Loader<Cursor>) {
           // setLiveData(null)
        }
    }


    fun setLiveData(result :List<Image>?){
        photosLiveData.value=result }


    companion object{
        private const val IMAGES_LOADER = 1

        fun getInstance(context: Context):PhotoLoader{
            return PhotoLoader(context) }
    }



}