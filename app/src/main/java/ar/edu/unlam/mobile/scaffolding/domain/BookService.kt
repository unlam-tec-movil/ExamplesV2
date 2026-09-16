package ar.edu.unlam.scaffoldingandroid3.domain

import ar.edu.unlam.mobile.scaffolding.domain.BookUseCases
import javax.inject.Inject

class BookService
    @Inject
    constructor(
        val repo: BookRepository,
    ) : BookUseCases {
        override fun getBooks(): List<Book> = repo.getBooks()
    }
