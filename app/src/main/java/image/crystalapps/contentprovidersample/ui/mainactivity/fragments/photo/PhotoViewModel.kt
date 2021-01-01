package image.crystalapps.contentprovidersample.ui.mainactivity.fragments.photo

import android.content.Context
import androidx.hilt.lifecycle.ViewModelInject
import image.crystalapps.contentprovidersample.PhotoLoader
import image.crystalapps.contentprovidersample.ui.base.BaseViewModel

class PhotoViewModel @ViewModelInject constructor(private  val photoRepository: PhotoRepository): BaseViewModel<PhotoNavigator>(photoRepository) {





    suspend fun getPhotoContentProvider(context : Context): PhotoLoader {
        return photoRepository.getPhotoContentProvider(context)
    }


}