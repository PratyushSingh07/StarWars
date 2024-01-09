package com.assignment.starwars.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assignment.starwars.repository.PlanetRepository
import com.assignment.starwars.ui.state.PlanetUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlanetViewModel @Inject constructor(private val planetRepositoryImpl: PlanetRepository) :
    ViewModel() {

    private var _planetUiState = MutableStateFlow<PlanetUiState>(PlanetUiState.Initial)
    val planetUiState: StateFlow<PlanetUiState> = _planetUiState

    fun getPlanets() {
        viewModelScope.launch(Dispatchers.IO) {
            _planetUiState.value = PlanetUiState.Loading
            planetRepositoryImpl.getPlanets().catch {
                _planetUiState.value = PlanetUiState.Error(it)
            }.collect {
                _planetUiState.value = PlanetUiState.Response(it.results)
            }
        }
    }

}