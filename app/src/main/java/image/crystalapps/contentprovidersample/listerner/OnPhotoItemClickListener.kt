package image.crystalapps.contentprovidersample.listerner

import android.view.View

interface OnPhotoItemClickListener<T> {

    fun clickItem(view : View, item :T, selectPosition :Int)

}