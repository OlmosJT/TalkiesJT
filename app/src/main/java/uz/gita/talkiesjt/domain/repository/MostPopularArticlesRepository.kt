package uz.gita.talkiesjt.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.gita.talkiesjt.data.model.ArticleData
import uz.gita.talkiesjt.utils.Resource

interface MostPopularArticlesRepository {
    fun getMostPopularArticles(): Flow<Resource<List<ArticleData>>>

    fun getTopStories(section: String): Flow<Resource<List<ArticleData>>>
}