package image.crystalapps.contentprovidersample.ui.mainactivity.fragments.albums

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import image.crystalapps.contentprovidersample.BR
import image.crystalapps.contentprovidersample.R
import image.crystalapps.contentprovidersample.databinding.AlbumsDataBinding
import image.crystalapps.contentprovidersample.ui.base.BaseFragment

@AndroidEntryPoint
class AlbumsFragment :BaseFragment<AlbumsViewModel, AlbumsDataBinding>() {

    private val mViewModel by viewModels<AlbumsViewModel>()

    private var mAlbumsDataBinding:AlbumsDataBinding?=null


    override fun getBindingVariable(): Int =BR.viewModel
    override fun getLayoutId(): Int =R.layout.albums_fragment

    override fun getViewModel(): AlbumsViewModel =mViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mAlbumsDataBinding =getViewDataBinding()

    }


}