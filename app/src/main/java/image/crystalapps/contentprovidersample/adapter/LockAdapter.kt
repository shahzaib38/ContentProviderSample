package image.crystalapps.contentprovidersample.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import image.crystalapps.contentprovidersample.R
import image.crystalapps.contentprovidersample.databinding.LockitemDataBinding
import image.crystalapps.contentprovidersample.databinding.VideoItemDataBinding
import image.crystalapps.contentprovidersample.entities.LockType


val mLockDiffUtils =object: DiffUtil.ItemCallback<LockType>(){
    override fun areItemsTheSame(oldItem: LockType, newItem: LockType): Boolean {
        return oldItem == newItem }

    override fun areContentsTheSame(oldItem: LockType, newItem: LockType): Boolean {

        return false }


}



class LockAdapter  :BaseAdapter<LockType , LockitemDataBinding>(mLockDiffUtils) {


    override fun createBinding(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): LockitemDataBinding = DataBindingUtil.inflate(inflater , R.layout.lock_item_layout,parent ,false)


    override fun bind(binding: LockitemDataBinding, item: LockType, position: Int) {

        binding.run {
            this.lockType = item }

    }

    override fun onDataChanged(values: Boolean) {


    }



}

