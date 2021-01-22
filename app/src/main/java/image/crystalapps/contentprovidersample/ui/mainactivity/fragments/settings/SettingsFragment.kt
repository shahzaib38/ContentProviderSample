package image.crystalapps.contentprovidersample.ui.mainactivity.fragments.settings

import android.icu.lang.UCharacter
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import image.crystalapps.contentprovidersample.BR
import image.crystalapps.contentprovidersample.R
import image.crystalapps.contentprovidersample.adapter.ImageAdapter
import image.crystalapps.contentprovidersample.adapter.SettingsAdapter
import image.crystalapps.contentprovidersample.databinding.SettingsDataBinding
import image.crystalapps.contentprovidersample.entities.SettingUtils
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

        initRecyclerView()

    }

   private fun initRecyclerView(){

       val settingAdapter = SettingsAdapter()

   val settingArray=    SettingUtils.getSettingList()

       if (settingArray.isNotEmpty()  ) {
           settingAdapter.submitList(settingArray)

           mSettingsDataBinding?.settingRecyclerView?.run {
               this.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
               this.adapter = settingAdapter
               this.isNestedScrollingEnabled = false }

       }

    }

}