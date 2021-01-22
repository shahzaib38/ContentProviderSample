package image.crystalapps.contentprovidersample.ui.mainactivity.fragments.settings

import androidx.hilt.lifecycle.ViewModelInject
import image.crystalapps.contentprovidersample.repo.SettingFragmentRepository
import image.crystalapps.contentprovidersample.ui.base.BaseViewModel

class SettingsViewModel @ViewModelInject constructor(private val settingFragmentRepository: SettingFragmentRepository) :BaseViewModel<SettingsNavigator>(settingFragmentRepository) {



}