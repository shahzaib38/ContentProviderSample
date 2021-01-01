package image.crystalapps.contentprovidersample.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import image.crystalapps.contentprovidersample.ui.mainactivity.MainActivity
import image.crystalapps.contentprovidersample.R
import image.crystalapps.contentprovidersample.databinding.ImageItemDataBinding
import image.crystalapps.contentprovidersample.entities.Image
import image.crystalapps.contentprovidersample.ui.mainactivity.fragments.photo.PhotoFragment
import kotlinx.android.synthetic.main.video_item.view.*


val  diffUtil =object : DiffUtil.ItemCallback<Image>() {
    override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean {
        return false
    }

    override fun areContentsTheSame(oldItem: Image , newItem: Image): Boolean {

        return false
    }
}


class ImageAdapter(private val photoFragment : PhotoFragment) :BaseAdapter<Image, ImageItemDataBinding>(diffUtil){
    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup, viewType: Int
    ): ImageItemDataBinding= DataBindingUtil.inflate(inflater , R.layout.image_item,parent ,false)


    override fun bind(binding: ImageItemDataBinding, item: Image ,position :Int) {
    Glide.with(binding.root).load(Uri.parse(item.uriImage)).into(binding.singleImageView)

        binding.singleImageView.setOnClickListener {
            photoFragment.clickItem(it ,currentList.toList(),position )

        }
    }

    override fun onDataChanged(values: Boolean) {


    }
}