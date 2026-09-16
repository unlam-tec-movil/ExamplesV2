package ar.edu.unlam.mobile.scaffolding.ui.screens.home

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.mobile.scaffolding.data.repositories.ArticleRepository
import ar.edu.unlam.mobile.scaffolding.ui.components.CardData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@Immutable
sealed interface ArticleUIState {
    data class Success(
        val cardList: List<CardData>,
    ) : ArticleUIState

    data object Loading : ArticleUIState

    data class Error(
        val message: String,
    ) : ArticleUIState
}

// Este UI State representa el estado de la pantalla de inicio.
data class HomeUIState(
    val articleState: ArticleUIState,
)

@HiltViewModel
class HomeViewModel
    @Inject
    constructor(
        private val repository: ArticleRepository,
    ) : ViewModel() {
        val userName: String = "Juan"
        val textito: String = "Hello $userName!"

        private val _uiState =
            MutableStateFlow(
                HomeUIState(
                    articleState = ArticleUIState.Loading,
                ),
            )
        val uiState = _uiState.asStateFlow()

        init {
            viewModelScope.launch {
                repository
                    .listArticles()
                    .map { articles ->
                        articles.sortedBy { it.title }
                    }.map { sortedArticles ->
                        sortedArticles.map { article ->
                            CardData(
                                title = article.title,
                                text = article.text,
                                image = article.image,
                                likes = article.likes,
                                commentsAmount = article.commentsAmount,
                            )
                        }
                    }.collect { cardList ->
                        _uiState.value =
                            HomeUIState(
                                articleState = ArticleUIState.Success(cardList),
                            )
                    }
            }
        }
    }
