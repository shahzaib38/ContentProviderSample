package image.crystalapps.contentprovidersample.ui.singleimage.pager_fragment

import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import image.crystalapps.contentprovidersample.BR
import image.crystalapps.contentprovidersample.R
import image.crystalapps.contentprovidersample.databinding.ImageSelectDataBinding
import image.crystalapps.contentprovidersample.entities.Image
import image.crystalapps.contentprovidersample.ui.base.BaseFragment
import image.crystalapps.contentprovidersample.ui.singleimage.SingleImageActivity
import image.crystalapps.contentprovidersample.ui.singleimage.SingleViewModel
import image.crystalapps.contentprovidersample.ui.singleimage.dialog_fragment.DetailsDialogFragment


@AndroidEntryPoint
class ImageFragmentSelect : BaseFragment<SingleViewModel, ImageSelectDataBinding>() {


    companion object {

        private const val IMAGE = "image"
        private var imageSelectDataBinding : ImageSelectDataBinding?=null

        fun newInstance(image: Image?): ImageFragmentSelect {
            val fragment = ImageFragmentSelect()
            val args = Bundle()
            args.putParcelable(IMAGE, image)
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var mSingleImageActivity : SingleImageActivity


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageSelectDataBinding = getViewDataBinding()
        val images=      arguments?.let { it.getParcelable<Image>(IMAGE) }

        imageSelectDataBinding?.imagedata?.setImageURI(Uri.parse(images?.uriImage))

        if(getBaseActivity() is SingleImageActivity) {
            mSingleImageActivity = getBaseActivity() as SingleImageActivity
        }else{
            throw ClassCastException("Login Dialog Fragment is null")
        }
        setHasOptionsMenu(true)
    }


    val mViewModel by  viewModels<SingleViewModel>()

    override fun getBindingVariable(): Int  =BR.viewModel

    override fun getLayoutId(): Int  = R.layout.image_select_layout

    override fun getViewModel(): SingleViewModel =mViewModel


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        inflater.inflate(R.menu.single_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }


    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
    }


    private fun openDetailsDialogFragment(){

        DetailsDialogFragment.getInstance().showDialog(mSingleImageActivity.supportFragmentManager)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when(item.itemId){

    R.id.details->{
    openDetailsDialogFragment()
    true }

    R.id.share-> {
//    val product = intent.getStringExtra("parcel")
//
//    if (product != null) {
//    shareSingleImage(Uri.parse(product)) }
    true }
    R.id.wallpaper ->{
//
//    val wallpaperManager=  WallpaperManager.getInstance(applicationContext)
//    val product = intent.getStringExtra("parcel")
//
//    val intent= wallpaperManager.getCropAndSetWallpaperIntent(Uri.parse(product))
//
//
//
//    if(wallpaperManager.isWallpaperSupported) {
//
//    //    if(wallpaperManager.isSetWallpaperAllowed) {
//    startActivityForResult(intent, 22)
//
//
//    //      }
//    }


    true
    }


    else-> false
    }
}