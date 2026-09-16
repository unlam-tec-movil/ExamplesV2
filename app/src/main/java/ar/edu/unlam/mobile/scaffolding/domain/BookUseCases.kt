package ar.edu.unlam.mobile.scaffolding.domain

import ar.edu.unlam.scaffoldingandroid3.domain.Book

interface BookUseCases {
    fun getBooks(): List<Book>
}
