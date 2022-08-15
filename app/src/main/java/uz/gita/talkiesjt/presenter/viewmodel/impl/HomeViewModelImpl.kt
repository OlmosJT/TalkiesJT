package uz.gita.talkiesjt.presenter.viewmodel.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.talkiesjt.data.model.ArticleData
import uz.gita.talkiesjt.domain.repository.MostPopularArticlesRepository
import uz.gita.talkiesjt.presenter.viewmodel.HomeViewModel
import uz.gita.talkiesjt.utils.Resource
import uz.gita.talkiesjt.utils.isConnected
import javax.inject.Inject

@HiltViewModel
class HomeViewModelImpl @Inject constructor(
    private val repository: MostPopularArticlesRepository
): ViewModel(), HomeViewModel {
    override val mostPopularArticlesLiveData = MutableLiveData<List<ArticleData>>()
    override val notConnectedLiveData = MutableLiveData<Boolean>()
    override val isLoadingLiveData = MutableLiveData<Boolean>()
    override val errorLiveData = MutableLiveData<String>()
    override val webViewScreenLiveData = MutableLiveData<ArticleData>()

    override fun loadMostPopularArticles() {
        if (isConnected()) {
            notConnectedLiveData.value = false
            repository.getMostPopularArticles().onEach {
                when(it) {
                    is Resource.Success -> {
                        mostPopularArticlesLiveData.value = it.data
                        isLoadingLiveData.value = false
                    }
                    is Resource.Error -> {
                        errorLiveData.value = it.message
                        isLoadingLiveData.value = false
                    }
                    is Resource.Loading -> {
                        isLoadingLiveData.value = true
                    }
                }
            }.launchIn(viewModelScope)
        } else {
            notConnectedLiveData.value = true
        }
    }

    override fun openWebViewScreen(data: ArticleData) {
        webViewScreenLiveData.value = data
    }

    override fun loadTopStories(section: String) {
        if(isConnected()){
            notConnectedLiveData.value = false
            repository.getTopStories(section).onEach {
                when(it) {
                    is Resource.Success -> {
                        mostPopularArticlesLiveData.value = it.data
                        isLoadingLiveData.value = false
                    }
                    is Resource.Error -> {
                        errorLiveData.value = it.message
                        isLoadingLiveData.value = false
                    }
                    is Resource.Loading -> {
                        isLoadingLiveData.value = true
                    }
                }
            }.launchIn(viewModelScope)
        } else {
            notConnectedLiveData.value = true
        }
    }

}