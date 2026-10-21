package ar.edu.unlam.mobile.scaffolding.ui.screens

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.mobile.scaffolding.data.repositories.CatFactRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@Immutable
sealed interface HelloMessageUIState {
    data class Success(
        val message: String,
    ) : HelloMessageUIState

    data object Loading : HelloMessageUIState

    data class Error(
        val message: String,
    ) : HelloMessageUIState
}

@Immutable
sealed interface CatFactUIState {
    data class Success(
        val fact: String,
    ) : CatFactUIState

    data object Loading : CatFactUIState

    data class Error(
        val message: String,
    ) : CatFactUIState
}

data class HomeUIState(
    val helloMessageState: HelloMessageUIState,
    val catFactState: CatFactUIState,
)

@HiltViewModel
class HomeViewModel
    @Inject
    constructor(
        private val catFactRepo: CatFactRepository,
    ) : ViewModel() {
        // Mutable State Flow contiene un objeto de estado mutable. Simplifica la operación de
        // actualización de información y de manejo de estados de una aplicación: Cargando, Error, Éxito
        // (https://developer.android.com/kotlin/flow/stateflow-and-sharedflow)
        // _helloMessage State es el estado del componente "HelloMessage" inicializado como "Cargando"
        private val helloMessage = MutableStateFlow(HelloMessageUIState.Loading)
        private val catFactState = MutableStateFlow(CatFactUIState.Loading)

        // _Ui State es el estado general del view model.
        private val _uiState =
            MutableStateFlow(
                HomeUIState(helloMessage.value, catFactState.value),
            )

        // UIState expone el estado anterior como un Flujo de Estado de solo lectura.
        // Esto impide que se pueda modificar el estado desde fuera del ViewModel.
        val uiState = _uiState.asStateFlow()

        init {
            viewModelScope.launch {
                _uiState.value = HomeUIState(HelloMessageUIState.Success("2b"), catFactState.value)
                catFactRepo.getCatFact().collect {
                    _uiState.value = HomeUIState(helloMessage.value, CatFactUIState.Success(it.fact))
                }
            }
        }
    }
