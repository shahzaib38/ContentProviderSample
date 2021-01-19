package image.crystalapps.contentprovidersample.ui.lock

import androidx.hilt.lifecycle.ViewModelInject
import image.crystalapps.contentprovidersample.ui.base.BaseViewModel

class LockViewModel  @ViewModelInject constructor(private val repository : LockRepository)  : BaseViewModel<LockNavigator>(repository) {




}