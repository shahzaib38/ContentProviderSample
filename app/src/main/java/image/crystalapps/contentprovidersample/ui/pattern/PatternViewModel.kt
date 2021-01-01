package image.crystalapps.contentprovidersample.ui.pattern

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import image.crystalapps.contentprovidersample.ui.base.BaseViewModel

class PatternViewModel  @ViewModelInject constructor(private val patternRepository: PatternRepository):BaseViewModel<PatternNavigator>(patternRepository) {


    var _PatterLockText = MutableLiveData<String>("Draw Unlock Pattern")
     val patternLockText =_PatterLockText

   fun setPatternText(text :String){
       _PatterLockText.value =text
       println(text)
    }

}