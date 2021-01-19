package image.crystalapps.contentprovidersample.ui.settings

import android.os.Bundle
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

    private var mSettingDataBinding: SettingDataBinding?=null



    override fun getBindingVariable(): Int =BR.viewModel

    override fun getLayoutId(): Int =R.layout.setting_activity
    override fun getViewModel(): SettingViewModel =mViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mSettingDataBinding = getViewDataBinding()

    }






}