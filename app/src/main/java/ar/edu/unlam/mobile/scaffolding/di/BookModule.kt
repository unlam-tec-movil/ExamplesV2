package ar.edu.unlam.mobile.scaffolding.di

import ar.edu.unlam.mobile.scaffolding.data.repositories.BookStubRepo
import ar.edu.unlam.mobile.scaffolding.domain.BookUseCases
import ar.edu.unlam.scaffoldingandroid3.domain.BookRepository
import ar.edu.unlam.scaffoldingandroid3.domain.BookService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class BookModule {
    @Binds
    abstract fun bindService(s: BookService): BookUseCases

    @Binds
    abstract fun bindRepository(r: BookStubRepo): BookRepository
}
