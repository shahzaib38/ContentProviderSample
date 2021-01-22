package image.crystalapps.contentprovidersample.ui.lock

import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import image.crystalapps.contentprovidersample.BR
import image.crystalapps.contentprovidersample.R
import image.crystalapps.contentprovidersample.adapter.ImageAdapter
import image.crystalapps.contentprovidersample.adapter.LockAdapter
import image.crystalapps.contentprovidersample.common.LockUtils
import image.crystalapps.contentprovidersample.databinding.LockDataBinding
import image.crystalapps.contentprovidersample.ui.base.BaseActivity



@AndroidEntryPoint
class LockActivity   : BaseActivity<LockViewModel , LockDataBinding>() {

    private val mViewModel by   viewModels<LockViewModel>()

    private var mLockDataBinding : LockDataBinding? =null


    override fun getBindingVariable(): Int  =BR.viewModel
    override fun getLayoutId(): Int  = R.layout.lock_activity
    override fun getViewModel(): LockViewModel  = mViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

      mLockDataBinding =  getViewDataBinding()

        val lockAdapter = LockAdapter()

     val result =   LockUtils.getLockUtils()


        if ( result.isNotEmpty()  ) {
            lockAdapter.submitList(result)

            mLockDataBinding?.lockRecyclerview?.run {


                this.layoutManager = LinearLayoutManager(this.context,  LinearLayoutManager.VERTICAL, false)
                this.adapter = lockAdapter
                val itemDecorator =
                    DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
                itemDecorator.setDrawable(
                    ContextCompat.getDrawable(
                        context,
                        R.drawable.divider
                    )!!
                )
                this.addItemDecoration(itemDecorator)
                this.isNestedScrollingEnabled = false } }




    }

}