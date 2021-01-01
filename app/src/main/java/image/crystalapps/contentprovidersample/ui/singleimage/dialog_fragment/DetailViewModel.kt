package image.crystalapps.contentprovidersample.ui.singleimage.dialog_fragment

import androidx.hilt.lifecycle.ViewModelInject
import image.crystalapps.contentprovidersample.ui.base.BaseViewModel

class DetailViewModel @ViewModelInject constructor(private val detailRepository :DetailRepository):BaseViewModel<DetailNavigator>(detailRepository) {



}