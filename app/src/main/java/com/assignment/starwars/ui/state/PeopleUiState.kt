package com.assignment.starwars.ui.state

import androidx.paging.PagingData
import com.assignment.starwars.models.Person

sealed class PeopleUiState {
    object Loading : PeopleUiState()
    object Initial : PeopleUiState()
    data class Response(val person: PagingData<Person>) : PeopleUiState()
    data class Error(val throwable: Throwable) : PeopleUiState()
}
