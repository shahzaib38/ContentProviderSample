package image.crystalapps.contentprovidersample.ui.singleimage

import android.app.WallpaperManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.core.net.toUri
import androidx.core.view.ViewCompat
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import image.crystalapps.contentprovidersample.BR
import image.crystalapps.contentprovidersample.R
import image.crystalapps.contentprovidersample.databinding.SingleImageDataBinding
import image.crystalapps.contentprovidersample.entities.Image
import image.crystalapps.contentprovidersample.ui.base.BaseActivity
import image.crystalapps.contentprovidersample.ui.singleimage.dialog_fragment.DetailsDialogFragment
import image.crystalapps.contentprovidersample.ui.singleimage.pager_fragment.PagerFragment
import kotlinx.android.synthetic.main.single_image_activity.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


@AndroidEntryPoint
class SingleImageActivity :BaseActivity<SingleViewModel,SingleImageDataBinding>() {



    companion object {
        val IMAGE_HEADER = "image:header"

    }



    private val mViewModel by viewModels<SingleViewModel>()

    override fun getBindingVariable(): Int =BR.viewModel

    override fun getLayoutId(): Int  =R.layout.single_image_activity

    override fun getViewModel(): SingleViewModel =mViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

     val _binding=    getViewDataBinding()

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setSupportActionBar(findViewById(R.id.toolbar))

        _binding?.root?.apply {
            ViewCompat.setTransitionName(this, IMAGE_HEADER) }


        val imageList = intent.getParcelableArrayListExtra<Image>("images_list")
     val   selectPosition =intent.getIntExtra("Select_Position",0)
        if(imageList!=null && imageList.isNotEmpty()) {
            setUpPagerFragment(imageList ,selectPosition)
        }




    }






    private fun setUpPagerFragment(products: ArrayList<Image>,selectPosition :Int){

        val pagerFragment=    PagerFragment.getInstance(products , selectPosition)
        pagerFragment.run {
            supportFragmentManager.beginTransaction().replace(R.id.multiImageContainer ,this).commit() }

    }




}