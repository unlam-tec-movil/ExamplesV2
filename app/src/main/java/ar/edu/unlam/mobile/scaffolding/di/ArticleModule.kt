package ar.edu.unlam.mobile.scaffolding.di

import ar.edu.unlam.mobile.scaffolding.data.repositories.ArticleRepository
import ar.edu.unlam.mobile.scaffolding.data.repositories.FakeArticleRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ArticleModule {
    @Binds
    abstract fun articleRepository(impl: FakeArticleRepository): ArticleRepository
}
