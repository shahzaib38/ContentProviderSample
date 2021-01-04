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
import image.crystalapps.contentprovidersample.entities.Video
import image.crystalapps.contentprovidersample.ui.mainactivity.fragments.videos.VideosFragment


val  diffUtilVideos =object : DiffUtil.ItemCallback<Video>() {
    override fun areItemsTheSame(oldItem: Video, newItem:Video): Boolean {
        return false
    }

    override fun areContentsTheSame(oldItem: Video, newItem: Video): Boolean {

        return false
    }
}


class VideoAdapter(private val videosFragment : VideosFragment) :BaseAdapter<Video, VideoItemDataBinding>(diffUtilVideos){
    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup, viewType: Int
    ): VideoItemDataBinding = DataBindingUtil.inflate(inflater , R.layout.video_item,parent ,false)


    override fun bind(binding: VideoItemDataBinding, item: Video,position:Int) {
        Glide.with(binding.root).load(Uri.parse(item.uriImage)).into(binding.singleImageView)

        binding.singleImageView.setOnClickListener {
            videosFragment.clickItem(it ,currentList.toList(),position)

        }
    }




    override fun onDataChanged(values: Boolean) {

    }
}