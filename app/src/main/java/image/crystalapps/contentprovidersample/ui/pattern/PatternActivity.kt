package image.crystalapps.contentprovidersample.ui.pattern

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import image.crystalapps.contentprovidersample.BR
import image.crystalapps.contentprovidersample.ui.mainactivity.MainActivity
import image.crystalapps.contentprovidersample.R
import image.crystalapps.contentprovidersample.databinding.PatternDataBinding
import image.crystalapps.contentprovidersample.pattercallbacks.OnPatternSetUp
import image.crystalapps.contentprovidersample.pattercallbacks.PatternCallBacks
import image.crystalapps.contentprovidersample.ui.base.BaseActivity

@AndroidEntryPoint
class PatternActivity :BaseActivity<PatternViewModel , PatternDataBinding>() {


    private val mViewModel by viewModels<PatternViewModel>()


    override fun getBindingVariable(): Int =BR.viewModel

    override fun getLayoutId(): Int =R.layout.pattern_layout
    override fun getViewModel(): PatternViewModel =mViewModel


    var patternDataBinding :PatternDataBinding?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


          patternDataBinding=   getViewDataBinding()
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setUpPattern()


    }


     fun getResourceString(i :Int): String {
        return resources.getString(i) }

    private fun setUpPattern(){
        patternDataBinding?.apply {
            this.patternView.addPatternLockListener(
                PatternCallBacks.getInstance(patternView, object : OnPatternSetUp() {
                    override fun onCorrect() {
                        viewModelText(getResourceString(R.string.correct_pattern))
                        val intent  = Intent(this@PatternActivity ,
                            MainActivity::class.java)
                        startActivity(intent) }

                    override fun inCorrect() {
                        viewModelText(getResourceString(R.string.incorrect_pattern)) }

                    override fun onProgress() {
                        viewModelText(getResourceString(R.string.black_pattern)) } })) }
    }


    fun viewModelText(text :String){
        patternDataBinding?.textView?.text=text
    }
}