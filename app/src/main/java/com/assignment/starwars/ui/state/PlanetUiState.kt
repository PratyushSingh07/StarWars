package com.assignment.starwars.ui.state

import com.assignment.starwars.models.Planet

sealed class PlanetUiState {

    object Loading : PlanetUiState()
    object Initial : PlanetUiState()
    data class Response(val planet: List<Planet>) : PlanetUiState()
    data class Error(val throwable: Throwable) : PlanetUiState()
}