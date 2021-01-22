package image.crystalapps.contentprovidersample.ui.settings

import androidx.hilt.lifecycle.ViewModelInject
import image.crystalapps.contentprovidersample.repo.SettingRepository
import image.crystalapps.contentprovidersample.ui.base.BaseViewModel

class SettingViewModel @ViewModelInject constructor(private  val settingRepository: SettingRepository) :BaseViewModel<SettingNavigator>(settingRepository) {


}