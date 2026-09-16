package ar.edu.unlam.mobile.scaffolding.domain

import ar.edu.unlam.scaffoldingandroid3.domain.Book
import ar.edu.unlam.scaffoldingandroid3.domain.BookRepository
import ar.edu.unlam.scaffoldingandroid3.domain.BookService
import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class BookServiceTest {
    @Mock
    lateinit var bookRepositoryMock: BookRepository

    @InjectMocks
    lateinit var bookService: BookService

    @Test
    fun `should return a list of books`() {
        val expected =
            listOf(
                Book("The Great Gatsby", "F. Scott Fitzgerald", 1925),
                Book("To Kill a Mockingbird", "Harper Lee", 1960),
                Book("1984", "George Orwell", 1949),
            )
        Mockito.`when`(bookRepositoryMock.getBooks()).thenReturn(expected)
        // When
        val actual = bookService.getBooks()
        // Then
        assertEquals(expected, actual)
    }
}
