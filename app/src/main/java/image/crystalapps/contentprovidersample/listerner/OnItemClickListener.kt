package image.crystalapps.contentprovidersample.listerner

import android.view.View

interface OnItemClickListener<T> {
    fun clickItem(view : View, item :T)

}