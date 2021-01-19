package image.crystalapps.contentprovidersample.pattercallbacks

import com.andrognito.patternlockview.PatternLockView
import com.andrognito.patternlockview.utils.PatternLockUtils

abstract class OnPatternSetUp :IPattern {


    fun onFinished(lockPreview :PatternLockView  ,pattern : MutableList<PatternLockView.Dot>){

        if(checkIfMatches(lockPreview,pattern)){
            onCorrect()
            clear(lockPreview)
        }else{

            inCorrect()
            clear(lockPreview)
        }

    }


    fun checkIfMatches(lockPreview: PatternLockView ,pattern: MutableList<PatternLockView.Dot>):Boolean{

     val pattern =   PatternLockUtils.patternToString(lockPreview,pattern)
        if(pattern == "012345678"){ return true }
        return false }



   private fun clear(lockPreview :PatternLockView ){
        lockPreview.clearPattern()
    }



}