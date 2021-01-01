package image.crystalapps.contentprovidersample.ui.settings

import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import image.crystalapps.contentprovidersample.BR
import image.crystalapps.contentprovidersample.R
import image.crystalapps.contentprovidersample.databinding.SettingDataBinding
import image.crystalapps.contentprovidersample.ui.base.BaseActivity
import image.crystalapps.contentprovidersample.ui.mainactivity.MainViewModel

@AndroidEntryPoint
class SettingsActivity :BaseActivity<SettingViewModel , SettingDataBinding>() {

    private val mViewModel by viewModels<SettingViewModel>()

    override fun getBindingVariable(): Int =BR.viewModel

    override fun getLayoutId(): Int =R.layout.setting_activity
    override fun getViewModel(): SettingViewModel =mViewModel

}