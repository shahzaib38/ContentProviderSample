package image.crystalapps.contentprovidersample.ui.mainactivity.fragments.videos

import android.os.Bundle
import android.view.View
import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import image.crystalapps.contentprovidersample.R
import image.crystalapps.contentprovidersample.adapter.VideoAdapter
import image.crystalapps.contentprovidersample.databinding.VideosDataBinding
import image.crystalapps.contentprovidersample.ui.base.BaseFragment
import kotlinx.coroutines.launch

@AndroidEntryPoint
class VideosFragment :BaseFragment<VideosViewModel , VideosDataBinding>() {


    private val mViewModel by viewModels<VideosViewModel>()
      private var mVideoDataBinding: VideosDataBinding?=null

    override fun getBindingVariable(): Int =BR.viewModel

    override fun getLayoutId(): Int =R.layout.videos_fragment

    override fun getViewModel(): VideosViewModel = mViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       mVideoDataBinding = getViewDataBinding()


        loadDataFromStorage()

    }


    private   fun loadDataFromStorage(){

        lifecycleScope.launch {
            val photoProvider = mViewModel.getVideoContentProvider(requireContext())
            photoProvider.init(this@VideosFragment)
            photoProvider.videosLiveData.observe(viewLifecycleOwner, Observer { result ->

                println("Photo Fragment ${result}")
                val videosAdapter = VideoAdapter(this@VideosFragment)
                if (result!=null && result.isNotEmpty()  ) {
                    videosAdapter.submitList(result)

                    mVideoDataBinding?.imageRecyclerVIew?.run {
                        this.layoutManager = GridLayoutManager(requireContext(), 2)
                        this.adapter = videosAdapter
                        this.isNestedScrollingEnabled = false } } }) }
    }

}