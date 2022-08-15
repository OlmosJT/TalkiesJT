package uz.gita.talkiesjt.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.gita.talkiesjt.BuildConfig
import uz.gita.talkiesjt.data.remote.api.MostPopularArticlesApi
import uz.gita.talkiesjt.data.remote.api.TopStoriesApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteModule {

    @[Provides Singleton]
    fun provideLoggerInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @[Provides Singleton]
    fun provideOkHttpClient(@ApplicationContext context: Context): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(provideLoggerInterceptor())
            .build()

    @[Provides Singleton]
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    // APIs
    @[Provides Singleton]
    fun provideMostPopularArticleApi(retrofit: Retrofit): MostPopularArticlesApi =
        retrofit.create(MostPopularArticlesApi::class.java)

    @[Provides Singleton]
    fun provideTopStoriesArticleApi(retrofit: Retrofit): TopStoriesApi =
        retrofit.create(TopStoriesApi::class.java)

}