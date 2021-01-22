package image.crystalapps.contentprovidersample.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import image.crystalapps.contentprovidersample.R
import image.crystalapps.contentprovidersample.databinding.SettingItemDataBinding
import image.crystalapps.contentprovidersample.entities.Setting


val  diffUtilSettings =object : DiffUtil.ItemCallback<Setting>() {
    override fun areItemsTheSame(oldItem: Setting, newItem: Setting): Boolean {
        return oldItem == newItem }

    override fun areContentsTheSame(oldItem: Setting, newItem: Setting): Boolean {
        return false } }


class SettingsAdapter  :BaseAdapter<Setting, SettingItemDataBinding>(diffUtilSettings) {

    val innerSettingAdapter =SettingInnerAdapter()

    override fun createBinding(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): SettingItemDataBinding {
        val binding=    DataBindingUtil.inflate<SettingItemDataBinding>(inflater, R.layout.setting_item, parent, false)

        binding?.innerRecyclerView?.run {
            this.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL,false)
            val itemDecorator =
                DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
            itemDecorator.setDrawable(
                ContextCompat.getDrawable(
                    context,
                    R.drawable.divider
                )!!
            )
            this.addItemDecoration(itemDecorator)
            this.isNestedScrollingEnabled = false
            this.adapter = innerSettingAdapter

        }



        println("create Binding ")
        return binding
    }

    override fun bind(binding: SettingItemDataBinding, item: Setting, position: Int) {
        println(" Binding ")

        innerSettingAdapter.submitList(item.arrayList)

        println(item.arrayList)

    }

    override fun onDataChanged(values: Boolean) {
    }


}