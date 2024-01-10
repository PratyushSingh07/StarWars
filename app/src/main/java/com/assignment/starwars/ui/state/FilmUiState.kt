package com.assignment.starwars.ui.state

import com.assignment.starwars.models.FilmResponse

sealed class FilmUiState {
    object Loading : FilmUiState()
    object Initial : FilmUiState()
    data class Response(val title: FilmResponse) : FilmUiState()
    data class Error(val throwable: Throwable) : FilmUiState()
}
