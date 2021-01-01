package image.crystalapps.contentprovidersample.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import image.crystalapps.contentprovidersample.R
import image.crystalapps.contentprovidersample.databinding.ImageItemDataBinding
import image.crystalapps.contentprovidersample.databinding.VideoItemDataBinding
import image.crystalapps.contentprovidersample.ui.mainactivity.fragments.videos.VideosFragment


val  diffUtilVideos =object : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem:String): Boolean {
        return false
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {

        return false
    }
}


class VideoAdapter(private val videosFragment : VideosFragment) :BaseAdapter<String, VideoItemDataBinding>(diffUtilVideos){
    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup, viewType: Int
    ): VideoItemDataBinding = DataBindingUtil.inflate(inflater , R.layout.video_item,parent ,false)


    override fun bind(binding: VideoItemDataBinding, item: String,position:Int) {
        Glide.with(binding.root).load(Uri.parse(item)).into(binding.singleImageView)

        binding.singleImageView.setOnClickListener {
//            photoFragment.clickItem(it ,item)
        }
    }

    override fun onDataChanged(values: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}