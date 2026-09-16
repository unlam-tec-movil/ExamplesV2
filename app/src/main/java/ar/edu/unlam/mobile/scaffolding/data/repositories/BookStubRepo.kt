package ar.edu.unlam.mobile.scaffolding.data.repositories

import ar.edu.unlam.scaffoldingandroid3.domain.Book
import ar.edu.unlam.scaffoldingandroid3.domain.BookRepository

class BookStubRepo : BookRepository {
    private val books =
        listOf(
            Book("The Great Gatsby", "F. Scott Fitzgerald", 1925),
            Book("To Kill a Mockingbird", "Harper Lee", 1960),
            Book("1984", "George Orwell", 1949),
            Book("Pride and Prejudice", "Jane Austen", 1813),
            Book("The Catcher in the Rye", "J.D. Salinger", 1951),
        )

    override fun getBooks(): List<Book> = books
}
