package ar.edu.unlam.scaffoldingandroid3.domain

data class Book(
    var title: String,
    var author: String,
    var year: Int,
)

fun Book.toUpper(): Book =
    Book(
        title.uppercase(),
        author.uppercase(),
        year,
    )
