package uz.gita.talkiesjt.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.talkiesjt.domain.repository.MostPopularArticlesRepository
import uz.gita.talkiesjt.domain.repository.impl.MostPopularArticlesRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @[Binds Singleton]
    fun provideMostPopularArticlesRepository(impl: MostPopularArticlesRepositoryImpl): MostPopularArticlesRepository
}