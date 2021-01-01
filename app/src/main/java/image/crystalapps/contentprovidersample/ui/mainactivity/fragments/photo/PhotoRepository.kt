package image.crystalapps.contentprovidersample.ui.mainactivity.fragments.photo

import android.content.Context
import image.crystalapps.contentprovidersample.PhotoLoader
import image.crystalapps.contentprovidersample.ui.base.BaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PhotoRepository @Inject constructor() :BaseRepository() {




    suspend fun getPhotoContentProvider(context : Context): PhotoLoader = withContext(Dispatchers.IO) {
        return@withContext PhotoLoader.getInstance(context)
    }



 //   fun resetLoader(){

      //  photoRepository.reset()

    //}

}