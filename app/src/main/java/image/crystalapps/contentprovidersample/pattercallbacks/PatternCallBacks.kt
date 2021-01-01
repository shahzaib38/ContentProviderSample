package image.crystalapps.contentprovidersample.pattercallbacks

import com.andrognito.patternlockview.PatternLockView
import com.andrognito.patternlockview.listener.PatternLockViewListener

 class PatternCallBacks private  constructor(private val lockPreview :PatternLockView ,private val onPatternSetUpCallBacks:OnPatternSetUpCallBacks) :PatternLockViewListener {


    override fun onComplete(pattern: MutableList<PatternLockView.Dot>?) {

        if(pattern!=null) {
            onPatternSetUpCallBacks.onFinished( lockPreview,pattern) }

    }

    override fun onCleared() {


    }

    override fun onStarted() {

    }

    override fun onProgress(progressPattern: MutableList<PatternLockView.Dot>?) {

        if(progressPattern!=null){
            onPatternSetUpCallBacks.onProgress()

        }
    }


    companion object{

        fun getInstance( lockPreview :PatternLockView , onPatternSetUpCallBacks:OnPatternSetUpCallBacks):PatternLockViewListener{
           return PatternCallBacks(lockPreview,onPatternSetUpCallBacks) }

    }

}