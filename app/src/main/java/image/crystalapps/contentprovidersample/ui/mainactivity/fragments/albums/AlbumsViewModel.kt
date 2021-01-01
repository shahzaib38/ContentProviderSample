package image.crystalapps.contentprovidersample.ui.mainactivity.fragments.albums

import androidx.hilt.lifecycle.ViewModelInject
import image.crystalapps.contentprovidersample.ui.base.BaseViewModel

class AlbumsViewModel @ViewModelInject constructor(private  val albumsRepository: AlbumsRepository) :BaseViewModel<AlbumsNavigator>(albumsRepository) {


}