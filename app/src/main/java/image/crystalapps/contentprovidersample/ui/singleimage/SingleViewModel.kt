package image.crystalapps.contentprovidersample.ui.singleimage

import androidx.hilt.lifecycle.ViewModelInject
import image.crystalapps.contentprovidersample.ui.base.BaseViewModel
import image.crystalapps.contentprovidersample.ui.singleimage.SingleImageNavigator
import image.crystalapps.contentprovidersample.ui.singleimage.SingleImageRespository

class SingleViewModel @ViewModelInject constructor(private  val singleImageRespository: SingleImageRespository) :BaseViewModel<SingleImageNavigator>(singleImageRespository) {


}