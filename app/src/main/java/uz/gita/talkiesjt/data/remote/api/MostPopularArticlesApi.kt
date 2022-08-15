package uz.gita.talkiesjt.data.remote.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import uz.gita.talkiesjt.BuildConfig
import uz.gita.talkiesjt.data.remote.objson.ResponseMostPopularArticles

interface MostPopularArticlesApi {

    @GET("mostpopular/v2/viewed/1.json")
    suspend fun requestMostPopularArticles(
        @Query("api-key") api_key: String = BuildConfig.API_KEY
    ): Response<ResponseMostPopularArticles>
}