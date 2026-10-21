package ar.edu.unlam.mobile.scaffolding.di

import ar.edu.unlam.mobile.scaffolding.data.repositories.CatFactDefaultRepository
import ar.edu.unlam.mobile.scaffolding.data.repositories.CatFactRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindCatFactRepository(impl: CatFactDefaultRepository): CatFactRepository
}
