package com.assignment.starwars.ui.film

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assignment.starwars.repository.FilmRepository
import com.assignment.starwars.ui.state.FilmUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FilmViewModel @Inject constructor(private val filmRepositoryImpl: FilmRepository) :
    ViewModel() {

    private var _filmUiState = MutableStateFlow<FilmUiState>(FilmUiState.Initial)
    val filmUiState: StateFlow<FilmUiState> = _filmUiState

    fun getFilms(filmUrl: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _filmUiState.value = FilmUiState.Loading
            filmRepositoryImpl.getFilms(filmUrl).catch {
                _filmUiState.value = FilmUiState.Error(it)
            }.collect {
                _filmUiState.value = FilmUiState.Response(it)
            }
        }
    }
}