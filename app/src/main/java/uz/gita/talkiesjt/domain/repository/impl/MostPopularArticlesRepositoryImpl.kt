package uz.gita.talkiesjt.domain.repository.impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.gita.talkiesjt.data.model.ArticleData
import uz.gita.talkiesjt.data.remote.Mapper.toArticleData
import uz.gita.talkiesjt.data.remote.api.MostPopularArticlesApi
import uz.gita.talkiesjt.data.remote.api.TopStoriesApi
import uz.gita.talkiesjt.domain.repository.MostPopularArticlesRepository
import uz.gita.talkiesjt.utils.Resource
import javax.inject.Inject

class MostPopularArticlesRepositoryImpl @Inject constructor(
    private val mostPopularArticlesApi: MostPopularArticlesApi,
    private val topStoriesApi: TopStoriesApi
): MostPopularArticlesRepository {
    override fun getMostPopularArticles(): Flow<Resource<List<ArticleData>>> = flow<Resource<List<ArticleData>>> {
        emit(Resource.Loading())
        val response = mostPopularArticlesApi.requestMostPopularArticles()

        if(response.isSuccessful) {
            response.body()?.let { _body ->
                val data = _body.results.map {
                    it.toArticleData()
                }
                emit(Resource.Success(data))
            }
        } else {
            emit(Resource.Error("Error occurred"))
        }

    }.catch { emit(Resource.Error(it.message.toString())) }.flowOn(Dispatchers.IO)


    override fun getTopStories(section: String)= flow<Resource<List<ArticleData>>> {
        emit(Resource.Loading())
        val response = topStoriesApi.requestTopStories(section = section)

        if(response.isSuccessful){
            response.body()?.let { _body ->
                val data = _body.results.map {
                    it.toArticleData()
                }
                emit(Resource.Success(data))
            }
        } else {
            emit(Resource.Error("Error occurred"))
        }

    }.catch { emit(Resource.Error(it.message.toString())) }.flowOn(Dispatchers.IO)
}