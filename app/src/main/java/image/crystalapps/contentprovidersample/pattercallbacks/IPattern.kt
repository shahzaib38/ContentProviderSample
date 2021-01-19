package image.crystalapps.contentprovidersample.pattercallbacks

import com.andrognito.patternlockview.PatternLockView


interface  IPattern {

    fun onCorrect()

    fun inCorrect()

    fun onProgress()

}