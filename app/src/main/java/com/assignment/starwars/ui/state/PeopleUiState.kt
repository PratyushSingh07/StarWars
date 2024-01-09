package com.assignment.starwars.ui.state

import com.assignment.starwars.models.Person

sealed class PeopleUiState {
    object Loading : PeopleUiState()
    object Initial : PeopleUiState()
    data class Response(val person: List<Person>) : PeopleUiState()
    data class Error(val throwable: Throwable) : PeopleUiState()
}
