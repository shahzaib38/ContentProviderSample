package image.crystalapps.contentprovidersample.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import image.crystalapps.contentprovidersample.R
import image.crystalapps.contentprovidersample.databinding.LockItemDataBinding
import image.crystalapps.contentprovidersample.databinding.VideoItemDataBinding
import image.crystalapps.contentprovidersample.entities.LockType



val diffUtilLockType  =object : DiffUtil.ItemCallback<LockType>(){

    override fun areItemsTheSame(oldItem: LockType, newItem: LockType): Boolean {

        return oldItem  ==newItem
    }

    override fun areContentsTheSame(oldItem: LockType, newItem: LockType): Boolean {
        return oldItem.uId==newItem.uId }


}


class LockAdapter : BaseAdapter<LockType , LockItemDataBinding>(diffUtilLockType) {
    override fun createBinding(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): LockItemDataBinding {
        val binding =  DataBindingUtil.inflate<LockItemDataBinding>(inflater, R.layout.lock_item_layout, parent, false)

        return binding
    }

    override fun bind(binding: LockItemDataBinding, item: LockType, position: Int) {
        binding.lockType=item }

    override fun onDataChanged(values: Boolean) {

    }
}