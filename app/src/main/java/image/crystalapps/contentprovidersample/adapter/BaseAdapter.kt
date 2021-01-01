package image.crystalapps.contentprovidersample.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import image.crystalapps.contentprovidersample.viewHolder.BaseViewHolder


abstract class BaseAdapter< M , VDB: ViewDataBinding>(diffUtil: DiffUtil.ItemCallback<M>) : ListAdapter<M, BaseViewHolder<VDB>>(diffUtil) {





    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<VDB> {
        val inflator = LayoutInflater.from(parent.context)

        val binding = createBinding(inflator, parent ,viewType)
        return BaseViewHolder(binding)
    }


    abstract fun createBinding(inflater: LayoutInflater, parent: ViewGroup, viewType: Int): VDB

    override fun onBindViewHolder(holder: BaseViewHolder<VDB>, position: Int) {
        bind(holder.mBinding, getItem(position)!!,position)
        holder.mBinding.executePendingBindings()

    }

    abstract fun bind(binding: VDB, item: M,position: Int)

    abstract fun onDataChanged(values: Boolean)

}


