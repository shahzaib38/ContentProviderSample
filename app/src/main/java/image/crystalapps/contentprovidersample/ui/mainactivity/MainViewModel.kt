package image.crystalapps.contentprovidersample.ui.mainactivity

import androidx.hilt.lifecycle.ViewModelInject
import image.crystalapps.contentprovidersample.repo.MainRepository
import image.crystalapps.contentprovidersample.ui.base.BaseViewModel

class MainViewModel @ViewModelInject constructor(private val mainRepository: MainRepository) :BaseViewModel<MainNavigator>(mainRepository) {








}