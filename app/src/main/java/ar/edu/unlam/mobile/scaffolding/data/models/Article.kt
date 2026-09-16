package ar.edu.unlam.mobile.scaffolding.data.models

data class Article(
    val title: String,
    val text: String,
    val image: String,
    val author: String,
    var likes: Int,
    var commentsAmount: Int,
)
