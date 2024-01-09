package com.assignment.starwars.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assignment.starwars.repository.StarshipRepository
import com.assignment.starwars.ui.state.StarshipUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StarshipViewModel @Inject constructor(private val starshipRepositoryImpl: StarshipRepository) :
    ViewModel() {

    private var _starshipUiState = MutableStateFlow<StarshipUiState>(StarshipUiState.Initial)
    val starshipUiState: StateFlow<StarshipUiState> = _starshipUiState

    fun getStarships() {
        viewModelScope.launch(Dispatchers.IO) {
            _starshipUiState.value = StarshipUiState.Loading
            starshipRepositoryImpl.getStarships().catch {
                _starshipUiState.value = StarshipUiState.Error(it)
            }.collect {
                _starshipUiState.value = StarshipUiState.Response(it.results)
            }
        }
    }
}