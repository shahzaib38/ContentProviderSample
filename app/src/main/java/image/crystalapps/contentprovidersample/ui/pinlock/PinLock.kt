package image.crystalapps.contentprovidersample.ui.pinlock

import android.os.Bundle
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import image.crystalapps.contentprovidersample.BR
import image.crystalapps.contentprovidersample.R
import image.crystalapps.contentprovidersample.databinding.PinLockDataBinding
import image.crystalapps.contentprovidersample.ui.base.BaseActivity

@AndroidEntryPoint
class PinLock : BaseActivity<PinViewModel, PinLockDataBinding>() {


    private val mViewModel  by viewModels<PinViewModel>()


    private var mPinDataBinding : PinLockDataBinding?= null

    override fun getBindingVariable(): Int =BR.viewModel

    override fun getLayoutId(): Int  = R.layout.pin_layout

    override fun getViewModel(): PinViewModel  = mViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       mPinDataBinding =  getViewDataBinding()



        init()



    }


    fun init(){

        mPinDataBinding?.apply {
            this.pinLock.attachIndicatorDots(this.indicatorDots)

        }


    }



}