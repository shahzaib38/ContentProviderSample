package image.crystalapps.contentprovidersample.ui.search

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import image.crystalapps.contentprovidersample.BR
import image.crystalapps.contentprovidersample.R
import image.crystalapps.contentprovidersample.databinding.SearchableDataBinding
import image.crystalapps.contentprovidersample.ui.base.BaseActivity
import image.crystalapps.contentprovidersample.view.MaterialSearchView

@AndroidEntryPoint
class SearchableActivity :BaseActivity<SearchableViewModel , SearchableDataBinding>() ,AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener , MaterialSearchView.OnVoiceClickedListener,MaterialSearchView.OnQueryTextListener  {

    private val mViewModel by viewModels<SearchableViewModel>()

    private var mSearchableDataBinding :SearchableDataBinding?=null

    override fun getBindingVariable(): Int = BR.viewModel

    override fun getLayoutId(): Int= R.layout.search_layout

    override fun getViewModel(): SearchableViewModel =mViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mSearchableDataBinding=  getViewDataBinding()

        initlializeListener()


    }

   private fun Toast(value:String){
        Toast.makeText(this,value ,Toast.LENGTH_SHORT).show() }


   private fun initlializeListener(){
        mSearchableDataBinding?.searchView?.run {
            this.setOnQueryListener(this@SearchableActivity)
            this.setOnVoiceClickedListener(this@SearchableActivity)
            this.setOnItemClickListener(this@SearchableActivity)
            this.setOnItemLongClickListener(this@SearchableActivity)
            this.setTintAlpha(123) } }








    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        Toast("ItemClicked") }

    override fun onVoiceClicked() {
        Toast("Voice Clicked") }

    override fun onItemLongClick(
        parent: AdapterView<*>?,
        view: View?,
        position: Int,
        id: Long
    ): Boolean {
        Toast("Item Long Clicked")
        return true
    }

    override fun onQueryTextSubmit(query: String): Boolean {

        return false }

    override fun onQueryTextChange(newText: String): Boolean {

        return false }


}