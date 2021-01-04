package image.crystalapps.contentprovidersample.ui.singlevideo.pager_fragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import image.crystalapps.contentprovidersample.entities.Image
import image.crystalapps.contentprovidersample.entities.Video
import image.crystalapps.contentprovidersample.ui.singleimage.pager_fragment.ImageFragmentSelect



class VideoPagerAdapter(private val fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager) {


    private var imageList :List<Video> = emptyList()

    override fun getItem(position: Int): Fragment {
        return VideoFragmentSelect.newInstance(imageList[position]) }

    override fun getCount(): Int {

        return imageList.size?:0
    }


    fun submitItem(imageList : List<Video>){

        this.imageList=imageList
        notifyDataSetChanged()
    }



}