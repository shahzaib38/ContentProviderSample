package image.crystalapps.contentprovidersample.listerner

import android.view.View

interface OnVideoItemClickListener<V> {


    fun clickItem(view : View, item :V, selectPosition :Int)



}