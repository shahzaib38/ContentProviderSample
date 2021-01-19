package image.crystalapps.contentprovidersample.callbacks

import android.database.Cursor
import androidx.loader.content.Loader
import image.crystalapps.contentprovidersample.common.ImageContract

abstract class BaseLoaderCallBack<T> :OnLoaderCallBack() {




    abstract fun onResult(result :T)

   abstract fun onReset(loader: Loader<Cursor>)

    override fun getSelections(): String?  = null


    override fun getSelectionsArgs(): Array<String?>? = null



    override fun getSortOrderSql(): String? =null
}