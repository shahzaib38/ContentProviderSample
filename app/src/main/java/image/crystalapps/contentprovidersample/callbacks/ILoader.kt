package image.crystalapps.contentprovidersample.callbacks

import android.net.Uri

interface ILoader {


    fun getSelectProjection(): Array<String>
    fun getQueryUri(): Uri
    fun getSortOrderSql(): String?
    fun getSelections(): String?
    fun getSelectionsArgs(): Array<String?>?

}