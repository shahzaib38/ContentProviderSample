package image.crystalapps.contentprovidersample.ui.search

import androidx.hilt.lifecycle.ViewModelInject
import image.crystalapps.contentprovidersample.repo.SearchableRepository
import image.crystalapps.contentprovidersample.ui.base.BaseViewModel

class SearchableViewModel  @ViewModelInject constructor  (private  val searchableRespository : SearchableRepository) :BaseViewModel<SearchableNavigator>(searchableRespository)  {


}