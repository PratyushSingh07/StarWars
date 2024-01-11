package com.assignment.starwars.ui.people

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.cachedIn
import com.assignment.starwars.models.Person
import com.assignment.starwars.models.PersonDB
import com.assignment.starwars.repository.PeopleRepository
import com.assignment.starwars.ui.filter.SortOption
import com.assignment.starwars.ui.state.PeopleUiState
import com.assignment.starwars.utils.PeoplePagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PeopleViewModel @Inject constructor(private val peopleRepositoryImpl: PeopleRepository) :
    ViewModel() {

    private var _peopleUiState = MutableStateFlow<PeopleUiState>(PeopleUiState.Initial)
    val peopleUiState: StateFlow<PeopleUiState> = _peopleUiState

    private val currentSortOption = MutableStateFlow(SortOption.NAME)

    fun getPeople() {
        viewModelScope.launch(Dispatchers.IO) {
            _peopleUiState.value = PeopleUiState.Loading
            peopleRepositoryImpl.getPeople().cachedIn(viewModelScope).catch {
                _peopleUiState.value = PeopleUiState.Error(it)
            }.collect {
                _peopleUiState.value = PeopleUiState.Response(it)
            }
        }
    }

    fun savePeople(personDBList: List<PersonDB>) {
        viewModelScope.launch {
            peopleRepositoryImpl.saveOffline(personDBList)
        }
    }

    fun getPeopleFromDatabase() {
        viewModelScope.launch {
            _peopleUiState.value = PeopleUiState.Loading
            peopleRepositoryImpl.getOfflinePeople().cachedIn(viewModelScope).catch {
                _peopleUiState.value = PeopleUiState.Error(it)
            }.collect {
                _peopleUiState.value = PeopleUiState.ResponseOffline(it)
            }
        }
    }

}