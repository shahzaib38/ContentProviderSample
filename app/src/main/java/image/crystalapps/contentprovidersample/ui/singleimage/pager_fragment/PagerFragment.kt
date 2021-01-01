package image.crystalapps.contentprovidersample.ui.singleimage.pager_fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.VisibleForTesting
import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import image.crystalapps.contentprovidersample.R
import image.crystalapps.contentprovidersample.databinding.PagerDataBinding
import image.crystalapps.contentprovidersample.entities.Image
import image.crystalapps.contentprovidersample.ui.base.BaseFragment
import image.crystalapps.contentprovidersample.ui.singleimage.SingleViewModel
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
          const val SELECT_POSITION="Select_Position"

        fun getInstance(imagesList :ArrayList<Image>, selectedPosition :Int): PagerFragment {
            val fragment = PagerFragment()
            val args = Bundle()
            args.putParcelableArrayList(IMAGES, imagesList)
            args.putInt(SELECT_POSITION,selectedPosition)
            fragment.arguments = args
            return fragment

        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mPagerDataBinding= getViewDataBinding()


        var selectedPosition = 0
        arguments?.let {
            product = it.getParcelableArrayList<Image>(IMAGES)
            selectedPosition =it.getInt(SELECT_POSITION)


        }?: throw NullPointerException("Argument is null")


        adapter = ImagePagerAdapter(childFragmentManager)

        product?.run {
            adapter.submitItem(this)
        }?: throw NullPointerException("Image PAger Adapter is null")
        pager.adapter = adapter

        pager.currentItem = selectedPosition




    }

}