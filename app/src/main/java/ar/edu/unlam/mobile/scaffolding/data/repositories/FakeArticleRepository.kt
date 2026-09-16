package ar.edu.unlam.mobile.scaffolding.data.repositories

import ar.edu.unlam.mobile.scaffolding.data.models.Article
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds

class FakeArticleRepository
    @Inject
    constructor() : ArticleRepository {
        val articles: List<Article> =
            listOf(
                Article(
                    title = "Card 1",
                    text = "This is the first card",
                    image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR1R7TuHCF6LrnpwHz9qwLqN3-HEO4rOKw42RdbLZDlGw&s=10",
                    author = "Author 1",
                    likes = 100,
                    commentsAmount = 5,
                ),
                Article(
                    title = "Card 2",
                    text = "This is the second card",
                    image = "https://biayakuliahukt.id/wp-content/uploads/2023/03/Jenis-Biaya-Kuliah-Selama-Menempuh-Pendidikan-di-Unlam.jpg",
                    author = "Author 2",
                    likes = 20,
                    commentsAmount = 10,
                ),
            )

        override fun listArticles(): Flow<List<Article>> =
            flow {
                while (true) {
                    delay(2000.milliseconds)
                    emit(articles)
                    articles[0].likes += 10
                    articles[0].commentsAmount += 2
                    articles[1].likes += 5
                    articles[1].commentsAmount += 1
                }
            }
    }
