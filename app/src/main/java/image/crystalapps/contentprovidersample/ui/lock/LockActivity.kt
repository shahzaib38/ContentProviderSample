package image.crystalapps.contentprovidersample.ui.lock

import android.os.Bundle
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import image.crystalapps.contentprovidersample.BR
import image.crystalapps.contentprovidersample.R
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






    }

}