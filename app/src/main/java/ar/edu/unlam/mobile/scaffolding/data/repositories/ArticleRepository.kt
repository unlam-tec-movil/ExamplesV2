package ar.edu.unlam.mobile.scaffolding.data.repositories

import ar.edu.unlam.mobile.scaffolding.data.models.Article
import kotlinx.coroutines.flow.Flow

interface ArticleRepository {
    fun listArticles(): Flow<List<Article>>
}
