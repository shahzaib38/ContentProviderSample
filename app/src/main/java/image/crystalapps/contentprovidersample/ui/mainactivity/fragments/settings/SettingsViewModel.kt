package image.crystalapps.contentprovidersample.ui.mainactivity.fragments.settings

import androidx.hilt.lifecycle.ViewModelInject
import image.crystalapps.contentprovidersample.ui.base.BaseViewModel

class SettingsViewModel @ViewModelInject constructor(private val settingsRepository: SettingsRepository) :BaseViewModel<SettingsNavigator>(settingsRepository) {



}