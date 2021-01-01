package image.crystalapps.contentprovidersample.callbacks

import android.database.Cursor
import androidx.loader.content.Loader

abstract class OnLoaderCallBack :ILoader{

    abstract fun  onLoadFinished(loader  : Loader<Cursor> ,data :Cursor)

    fun onLoaderReset(){

    }

    abstract fun resetLoader(loader: Loader<Cursor>)


}