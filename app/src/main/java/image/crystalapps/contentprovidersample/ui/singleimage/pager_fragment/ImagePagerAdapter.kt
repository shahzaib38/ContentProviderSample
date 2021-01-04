package image.crystalapps.contentprovidersample.ui.singleimage.pager_fragment

import android.net.Uri
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import dagger.hilt.android.AndroidEntryPoint
import image.crystalapps.contentprovidersample.entities.Image

class ImagePagerAdapter(private val fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager) {


    private var imageList :List<Image> = emptyList()

    override fun getItem(position: Int): Fragment {
        return ImageFragmentSelect.newInstance(imageList[position]) }

    override fun getCount(): Int {

        return imageList.size?:0
    }


    fun submitItem(imageList : List<Image>){

        this.imageList=imageList
        notifyDataSetChanged()
    }



}