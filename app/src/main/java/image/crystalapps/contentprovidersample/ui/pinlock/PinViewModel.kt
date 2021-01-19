package image.crystalapps.contentprovidersample.ui.pinlock

import androidx.hilt.lifecycle.ViewModelInject
import image.crystalapps.contentprovidersample.ui.base.BaseViewModel

class PinViewModel @ViewModelInject constructor(private val pinRepository: PinRepository)  :BaseViewModel<PinNavigator>(pinRepository) {




}