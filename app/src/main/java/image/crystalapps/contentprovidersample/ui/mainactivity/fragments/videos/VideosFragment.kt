package image.crystalapps.contentprovidersample.ui.mainactivity.fragments.videos

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import image.crystalapps.contentprovidersample.BR
import image.crystalapps.contentprovidersample.R
import image.crystalapps.contentprovidersample.adapter.VideoAdapter
import image.crystalapps.contentprovidersample.databinding.VideosDataBinding
import image.crystalapps.contentprovidersample.entities.Video
import image.crystalapps.contentprovidersample.listerner.OnVideoItemClickListener
import image.crystalapps.contentprovidersample.ui.base.BaseFragment
import image.crystalapps.contentprovidersample.ui.mainactivity.MainActivity
import image.crystalapps.contentprovidersample.ui.singleimage.SingleImageActivity
import image.crystalapps.contentprovidersample.ui.singlevideo.SingleVideoPlayer
import kotlinx.android.synthetic.main.material_toolbar.view.*
import kotlinx.coroutines.launch
import java.util.*


@AndroidEntryPoint
class VideosFragment :BaseFragment<VideosViewModel , VideosDataBinding>() ,OnVideoItemClickListener<List<Video>>{


    private val mViewModel by viewModels<VideosViewModel>()
      private var mVideoDataBinding: VideosDataBinding?=null

    override fun getBindingVariable(): Int =BR.viewModel

    override fun getLayoutId(): Int =R.layout.videos_fragment

    override fun getViewModel(): VideosViewModel = mViewModel

    private var mMainActivity : MainActivity?=null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       mVideoDataBinding = getViewDataBinding()

        if(getBaseActivity() is MainActivity) {
            mMainActivity = getBaseActivity() as MainActivity
        }else{
            throw ClassCastException("Login Dialog Fragment is null")
        }

//        setUpToolbar()

        loadDataFromStorage()

    }


    fun setUpToolbar(){

        mVideoDataBinding?.mainToolbar?.run {
            this.toolbar.title = resources.getString(R.string.videos)
            mMainActivity?.setSupportActionBar(this as Toolbar)

        }
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

    override fun clickItem(view: View, item: List<Video>, selectPosition: Int) {
        val activityOption = ActivityOptionsCompat.makeSceneTransitionAnimation(
            requireActivity(),
            Pair(
                view.findViewById<ImageView>(R.id.singleImageView),
                SingleImageActivity.IMAGE_HEADER))

        val intent = Intent(requireActivity(), SingleVideoPlayer::class.java)

        Toast.makeText(requireContext() ,selectPosition.toString() , Toast.LENGTH_SHORT).show()

        intent.putExtra("Select_Position",selectPosition)
        intent.putParcelableArrayListExtra("videos_list", ArrayList<Video>(item))

        ActivityCompat.startActivity(requireContext(), intent, activityOption.toBundle())

    }

}