package image.crystalapps.contentprovidersample.ui.mainactivity.fragments.settings

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import image.crystalapps.contentprovidersample.BR
import image.crystalapps.contentprovidersample.R
import image.crystalapps.contentprovidersample.databinding.SettingsDataBinding
import image.crystalapps.contentprovidersample.ui.base.BaseFragment


@AndroidEntryPoint
class SettingsFragment  : BaseFragment<SettingsViewModel,SettingsDataBinding>() {

    private val  mViewModel by  viewModels<SettingsViewModel>()

    private  var mSettingsDataBinding  :SettingsDataBinding? = null


    override fun getBindingVariable(): Int  = BR.viewModel

    override fun getLayoutId(): Int  =R.layout.settings_fragment

    override fun getViewModel(): SettingsViewModel = mViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

      mSettingsDataBinding =   getViewDataBinding()


    }

}