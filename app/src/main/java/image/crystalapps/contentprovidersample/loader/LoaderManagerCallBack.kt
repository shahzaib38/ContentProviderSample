package image.crystalapps.contentprovidersample.loader

import android.content.Context
import android.database.Cursor
import android.os.Bundle
import androidx.loader.app.LoaderManager
import androidx.loader.content.Loader
import image.crystalapps.contentprovidersample.callbacks.OnLoaderCallBack
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class LoaderManagerCallBack(private val context :Context , private val onLoaderCallBack: OnLoaderCallBack) : LoaderManager.LoaderCallbacks<Cursor> {

    val scope = CoroutineScope(Dispatchers.IO )

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> {
        return BaseCursorLoader(context , onLoaderCallBack)
    }



      fun reset(){
          this.onLoaderCallBack.onLoaderReset() }

    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
        println("OnLoader Finished")
        if(data!=null) {


            scope.launch {
                onLoaderCallBack.onLoadFinished(loader, data)
            }

        }


        }

    override fun onLoaderReset(loader: Loader<Cursor>) {
        onLoaderCallBack.resetLoader(loader)
        println("OnLoader Reset")

    }


}