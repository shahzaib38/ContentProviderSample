package image.crystalapps.contentprovidersample.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import image.crystalapps.contentprovidersample.R
import image.crystalapps.contentprovidersample.databinding.ImageItemDataBinding
import image.crystalapps.contentprovidersample.entities.Image
import image.crystalapps.contentprovidersample.listerner.OnPhotoItemClickListener


val  diffUtil =object : DiffUtil.ItemCallback<Image>() {
    override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean {
        return oldItem == newItem }

    override fun areContentsTheSame(oldItem: Image , newItem: Image): Boolean {
        return oldItem.id==newItem.id }
}


class ImageAdapter(private val onPhotoClickListener :OnPhotoItemClickListener<List<Image>>) :BaseAdapter<Image, ImageItemDataBinding>(diffUtil){

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup, viewType: Int): ImageItemDataBinding
    {
        val binding=    DataBindingUtil.inflate<ImageItemDataBinding>(inflater, R.layout.image_item, parent, false)

        binding.root.setOnClickListener {
          val choosen=   binding.image
           if(choosen!=null) {

         //      onPhotoClickListener.clickItem(it.context ,it)
           }
        }
        return binding }

    override fun bind(binding: ImageItemDataBinding, item: Image ,position :Int) {
        binding.singleImageView.apply {
            binding.image = item

        }
    }

    override fun onDataChanged(values: Boolean) {

    }

}