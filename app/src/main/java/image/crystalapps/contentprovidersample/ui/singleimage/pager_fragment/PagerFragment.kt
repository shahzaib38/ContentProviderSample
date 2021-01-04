package image.crystalapps.contentprovidersample.ui.singleimage.pager_fragment

import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.widget.Toast
import androidx.annotation.VisibleForTesting
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import image.crystalapps.contentprovidersample.BR
import image.crystalapps.contentprovidersample.R
import image.crystalapps.contentprovidersample.common.ContentProviderUtils
import image.crystalapps.contentprovidersample.common.ContentProviderUtils.IMAGES_TYPE
import image.crystalapps.contentprovidersample.common.ContentProviderUtils.VIDEO_TYPES
import image.crystalapps.contentprovidersample.databinding.PagerDataBinding
import image.crystalapps.contentprovidersample.entities.Image
import image.crystalapps.contentprovidersample.entities.Video
import image.crystalapps.contentprovidersample.ui.base.BaseFragment
import image.crystalapps.contentprovidersample.ui.singleimage.SingleViewModel
import image.crystalapps.contentprovidersample.ui.singlevideo.pager_fragment.VideoPagerAdapter
import kotlinx.android.synthetic.main.pager_fragment.*

@AndroidEntryPoint
class PagerFragment :BaseFragment<SingleViewModel, PagerDataBinding>() {


   private val mViewModel by  viewModels<SingleViewModel>()

    private var mPagerDataBinding :PagerDataBinding?=null

    override fun getBindingVariable(): Int =BR.viewModel

    override fun getLayoutId(): Int =R.layout.pager_fragment
    override fun getViewModel(): SingleViewModel = mViewModel

    private var product: List<Image>? = null
    private lateinit var adapter: ImagePagerAdapter


    companion object{

        @VisibleForTesting
        const val  IMAGES ="images"
        const val VIDEOS="videos"
        const val SELECT_POSITION="Select_Position"

        const val COMMON_TYPE="common_type"

        fun <T : Parcelable> getInstance(imagesList :ArrayList<T>, selectedPosition :Int , mediaType :String): PagerFragment {
            val fragment = PagerFragment()
            if(mediaType== IMAGES_TYPE) {
                val args = Bundle()
                args.putString(COMMON_TYPE,mediaType)
                args.putParcelableArrayList(IMAGES, imagesList)
                args.putInt(SELECT_POSITION, selectedPosition)
                fragment.arguments = args
            }else if(mediaType == VIDEO_TYPES){
                val args = Bundle()
                args.putString(COMMON_TYPE,mediaType)
                args.putParcelableArrayList(VIDEOS, imagesList)
                args.putInt(SELECT_POSITION, selectedPosition)
                fragment.arguments = args
            }

            return fragment }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mPagerDataBinding= getViewDataBinding()


        var selectedPosition = 0
        arguments?.let {
            selectedPosition =it.getInt(SELECT_POSITION)
            val mediaType =it.getString(COMMON_TYPE)

           if(mediaType!=null){

               if(mediaType==IMAGES_TYPE){
                 val  product = it.getParcelableArrayList<Image>(IMAGES)

                   setImageAdapter(selectedPosition ,product)

               }else if(mediaType==VIDEO_TYPES){
                   val  product = it.getParcelableArrayList<Video>(VIDEOS)

                   setVideoAdapter(selectedPosition ,product)

               }

           }



        }?: throw NullPointerException("Argument is null")





    }

    fun setVideoAdapter(selectedPosition: Int , product : List<Video>?){
       val adapter = VideoPagerAdapter(childFragmentManager)
        product?.run {
            adapter.submitItem(this)
        }?: throw NullPointerException("Image PAger Adapter is null")

        pager.adapter = adapter

        pager.currentItem = selectedPosition


    }

    fun setImageAdapter(selectedPosition: Int , product : List<Image>?){
      val  adapter = ImagePagerAdapter(childFragmentManager)
        product?.run {
            adapter.submitItem(this)
        }?: throw NullPointerException("Image PAger Adapter is null")

        pager.adapter = adapter

        pager.currentItem = selectedPosition
    }

}