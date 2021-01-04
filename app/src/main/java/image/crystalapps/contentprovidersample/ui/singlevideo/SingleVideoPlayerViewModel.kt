package image.crystalapps.contentprovidersample.ui.singlevideo

import androidx.hilt.lifecycle.ViewModelInject
import image.crystalapps.contentprovidersample.ui.base.BaseViewModel
import javax.inject.Inject

class SingleVideoPlayerViewModel  @ViewModelInject constructor(private val singleVideoRepository: SingleVideoRepository) :BaseViewModel<SingleVideoNavigator>(singleVideoRepository) {


}