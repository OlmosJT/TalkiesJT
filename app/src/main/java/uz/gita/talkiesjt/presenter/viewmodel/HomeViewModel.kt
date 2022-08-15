package uz.gita.talkiesjt.presenter.viewmodel

import androidx.lifecycle.LiveData
import uz.gita.talkiesjt.data.model.ArticleData

interface HomeViewModel {
    val mostPopularArticlesLiveData: LiveData<List<ArticleData>>
    val notConnectedLiveData: LiveData<Boolean>
    val isLoadingLiveData: LiveData<Boolean>
    val errorLiveData: LiveData<String>
    val webViewScreenLiveData: LiveData<ArticleData>

    fun loadMostPopularArticles()
    fun openWebViewScreen(data: ArticleData)
    fun loadTopStories(section: String)
}