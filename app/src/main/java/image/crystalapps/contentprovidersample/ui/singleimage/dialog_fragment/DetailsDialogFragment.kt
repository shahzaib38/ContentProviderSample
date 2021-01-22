package image.crystalapps.contentprovidersample.ui.singleimage.dialog_fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import image.crystalapps.contentprovidersample.BR
import image.crystalapps.contentprovidersample.R
import image.crystalapps.contentprovidersample.databinding.DetailDataBinding
import image.crystalapps.contentprovidersample.entities.Details
import image.crystalapps.contentprovidersample.ui.base.BaseDialogFragment

@AndroidEntryPoint
class DetailsDialogFragment :BaseDialogFragment<DetailViewModel , DetailDataBinding>() {



    companion object{
        private const val TAG :String= "DetailsDialogFragment"
        private const val DETAILS = "DETAILS"

        fun getInstance(details  : Details): DetailsDialogFragment {
            val fragmentDialog = DetailsDialogFragment()
            val bundle = Bundle()
            bundle.putParcelable(DETAILS, details)
            fragmentDialog.arguments = bundle
            return fragmentDialog }



    }


    private val mViewModel by viewModels<DetailViewModel>()
      private  var mDetailDataBinding: DetailDataBinding?=null

    override fun getBindingVariable(): Int =BR.viewModel

    override fun getViewModel(): DetailViewModel? =mViewModel

    override fun getLayoutId(): Int =R.layout.detail_dialog_fragment


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

         mDetailDataBinding = getViewDataBinding()

        val arguments = arguments?.run {
            this.getParcelable<Details>(DETAILS)
        }?: throw NullPointerException("Not parcelable")

        mDetailDataBinding?.apply {

            this.details =arguments
        }



     }


    override fun showDialog(fragment: FragmentManager) {
        super.show(fragment,TAG) }




    override fun dismiss() {
        super.dismissDialog(TAG)
    }

}