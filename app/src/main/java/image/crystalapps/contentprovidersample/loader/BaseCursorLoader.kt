package image.crystalapps.contentprovidersample.loader

import android.content.Context
import androidx.loader.content.CursorLoader
import image.crystalapps.contentprovidersample.callbacks.ILoader

class BaseCursorLoader(context :Context ,iLoader :ILoader) :CursorLoader(context) {

    init {
        projection = iLoader.getSelectProjection();
        uri = iLoader.getQueryUri();
        selection = iLoader.getSelections()
        selectionArgs = iLoader.getSelectionsArgs()
        sortOrder = iLoader.getSortOrderSql();

    }





}