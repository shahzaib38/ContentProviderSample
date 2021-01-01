package image.crystalapps.contentprovidersample.ui.mainactivity.fragments.photo

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityOptionsCompat
import androidx.core.content.ContextCompat
import androidx.core.util.Pair
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import image.crystalapps.contentprovidersample.BR
import image.crystalapps.contentprovidersample.R
import image.crystalapps.contentprovidersample.adapter.ImageAdapter
import image.crystalapps.contentprovidersample.databinding.PhotoDataBinding
import image.crystalapps.contentprovidersample.entities.Image
import image.crystalapps.contentprovidersample.listerner.OnItemClickListener
import image.crystalapps.contentprovidersample.listerner.OnPhotoItemClickListener
import image.crystalapps.contentprovidersample.ui.base.BaseFragment
import image.crystalapps.contentprovidersample.ui.mainactivity.MainActivity
import image.crystalapps.contentprovidersample.ui.mainactivity.MainViewModel
import image.crystalapps.contentprovidersample.ui.singleimage.SingleImageActivity
import kotlinx.coroutines.launch
import java.util.ArrayList

@AndroidEntryPoint
class PhotoFragment  : BaseFragment<PhotoViewModel , PhotoDataBinding>() ,
    OnPhotoItemClickListener<List<Image>> {


    private val mViewModel by viewModels<PhotoViewModel>()
     private  var mPhotoDataBinding :PhotoDataBinding?=null

    override fun getBindingVariable(): Int =BR.viewModel

    override fun getLayoutId(): Int=R.layout.photo_fragment

    override fun getViewModel(): PhotoViewModel =mViewModel



    companion object {
        private const val IMAGES_LOADER = 1
        private const val EXTERNAL_STORAGE_PERMISSION = 9988
        private  val EXTERNAL_STORAGE_ARRAY:Array<String?>? = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

     mPhotoDataBinding =    getViewDataBinding()

        if (hasPermissions(requireContext(), EXTERNAL_STORAGE_ARRAY))
        { loadDataFromStorage() }
        else{ requestCameraPermission() }


    }
    private fun requestCameraPermission() {
        requestPermissions(
            EXTERNAL_STORAGE_ARRAY
                ?:arrayOf(Manifest.permission.CAMERA),
            EXTERNAL_STORAGE_PERMISSION
        ) }

    private fun hasPermissions(context: Context?, permissions: Array<String?>?): Boolean {
        if (context != null && permissions != null) {
            for (permission in permissions) {
                if (ContextCompat.checkSelfPermission(
                        context,
                        permission!!
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    return false
                }
            }
        }
        return true
    }
    private   fun loadDataFromStorage(){

        lifecycleScope.launch {
            val photoProvider = mViewModel.getPhotoContentProvider(requireContext())
            photoProvider.init(this@PhotoFragment)
            photoProvider.photosLiveData.observe(viewLifecycleOwner, Observer { result ->

                println("Photo Fragment ${result}")
                val imageAdapter = ImageAdapter(this@PhotoFragment)
                if (result!=null && result.isNotEmpty()  ) {
                    imageAdapter.submitList(result)

                    mPhotoDataBinding?.imageRecyclerVIew?.run {
                        this.layoutManager = GridLayoutManager(requireContext(), 2)
                        this.adapter = imageAdapter
                        this.isNestedScrollingEnabled = false } } }) }
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == EXTERNAL_STORAGE_PERMISSION) {
            if (grantResults.isNotEmpty()) {
                val cameraPermission = grantResults[0] == PackageManager.PERMISSION_GRANTED
                if (cameraPermission ) {
                    loadDataFromStorage()

                } else {
                    requireActivity().finish()
                    Toast.makeText(requireContext() ,"Permission Denied" , Toast.LENGTH_LONG).show()
                }
            }
        }
    }


    override fun clickItem(view: View, item: List<Image>, selectPosition: Int) {
        val activityOption = ActivityOptionsCompat.makeSceneTransitionAnimation(
            requireActivity(),
            Pair(
                view.findViewById<ImageView>(R.id.singleImageView),
                SingleImageActivity.IMAGE_HEADER))

        val intent = Intent(requireActivity(), SingleImageActivity::class.java)

        Toast.makeText(requireContext() ,selectPosition.toString() ,Toast.LENGTH_SHORT).show()

        intent.putExtra("Select_Position",selectPosition)
        intent.putParcelableArrayListExtra("images_list", ArrayList<Image>(item))

        ActivityCompat.startActivity(requireContext(), intent, activityOption.toBundle()) }


    override fun onResume() {
        super.onResume()
         println("Resume")


    }

    override fun onStart() {
        super.onStart()
        println("OnStart") }

    override fun onPause() {
        super.onPause()



        println("OnPause") }


}