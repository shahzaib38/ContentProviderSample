package image.crystalapps.contentprovidersample.ui.singlevideo

import androidx.hilt.lifecycle.ViewModelInject
import image.crystalapps.contentprovidersample.repo.SingleVideoRepository
import image.crystalapps.contentprovidersample.ui.base.BaseViewModel

class SingleVideoPlayerViewModel  @ViewModelInject constructor(private val singleVideoRepository: SingleVideoRepository) :BaseViewModel<SingleVideoNavigator>(singleVideoRepository) {


}