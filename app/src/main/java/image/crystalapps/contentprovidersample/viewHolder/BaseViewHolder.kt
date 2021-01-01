package image.crystalapps.contentprovidersample.viewHolder

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView


open class BaseViewHolder<VB : ViewDataBinding>(binding: VB) : RecyclerView.ViewHolder(binding.root) {

    var  mBinding :VB = binding

}

