package uz.gita.talkiesjt.data.remote.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import uz.gita.talkiesjt.BuildConfig
import uz.gita.talkiesjt.data.remote.response.TopStoriesResult

interface TopStoriesApi {

    @GET("topstories/v2/{section}.json")
    suspend fun requestTopStories(
        @Path("section") section: String,
        @Query("api-key") api_key: String = BuildConfig.API_KEY
    ): Response<TopStoriesResult.TopStoriesResponse>
}