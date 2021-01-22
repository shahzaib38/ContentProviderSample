package image.crystalapps.contentprovidersample.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import image.crystalapps.contentprovidersample.R
import image.crystalapps.contentprovidersample.databinding.SettingInnerDataBinding
import image.crystalapps.contentprovidersample.entities.SettingItems

val  diffUtilSettingItems =object : DiffUtil.ItemCallback<SettingItems>() {
    override fun areItemsTheSame(oldItem: SettingItems, newItem: SettingItems): Boolean {
        return false

    }

    override fun areContentsTheSame(oldItem: SettingItems, newItem: SettingItems): Boolean {
        return false

    } }


class SettingInnerAdapter : BaseAdapter<SettingItems , SettingInnerDataBinding>(diffUtilSettingItems) {
    override fun createBinding(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): SettingInnerDataBinding {
        val binding=    DataBindingUtil.inflate<SettingInnerDataBinding>(inflater, R.layout.setting_inner_layout, parent, false)

        return binding

    }

    override fun bind(binding: SettingInnerDataBinding, item: SettingItems, position: Int) {
        binding.settingItem =item
        println(item.title)

    }

    override fun onDataChanged(values: Boolean) {

    }


}