package com.assignment.starwars.ui.state

import com.assignment.starwars.models.Starship

sealed class StarshipUiState {
    object Loading : StarshipUiState()
    object Initial : StarshipUiState()
    data class Response(val starship: List<Starship>) : StarshipUiState()
    data class Error(val throwable: Throwable) : StarshipUiState()
}
