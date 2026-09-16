package ar.edu.unlam.mobile.scaffolding.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ar.edu.unlam.mobile.scaffolding.ui.components.CustomCard

const val HOME_SCREEN_ROUTE = "home"

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    val homeScreenState = viewModel.uiState.collectAsStateWithLifecycle()

    Column {
        Text(text = viewModel.textito)
        when (val articleState = homeScreenState.value.articleState) {
            is ArticleUIState.Loading -> {
                CircularProgressIndicator()
            }

            is ArticleUIState.Success -> {
                LazyColumn {
                    items(articleState.cardList.size) { index ->
                        CustomCard(
                            data = articleState.cardList[index],
                        )
                    }
                }
            }

            is ArticleUIState.Error -> {
                // Show error message
            }
        }
    }
}
