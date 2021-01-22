package image.crystalapps.contentprovidersample.repo

import android.content.Context
import image.crystalapps.contentprovidersample.PhotoLoader
import image.crystalapps.contentprovidersample.repo.BaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PhotoRepository @Inject constructor() :
    BaseRepository() {




    suspend fun getPhotoContentProvider(context : Context): PhotoLoader = withContext(Dispatchers.IO) {
        return@withContext PhotoLoader.getInstance(context)
    }



 //   fun resetLoader(){

      //  photoRepository.reset()

    //}

}