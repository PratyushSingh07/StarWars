package com.assignment.starwars.repository

import com.assignment.starwars.api.StarWarsApiService
import com.assignment.starwars.models.PeopleResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PeopleRepositoryImpl @Inject constructor(private val starWarsApiService: StarWarsApiService) :
    PeopleRepository {

    override fun getPeople(): Flow<PeopleResponse> {
        return flow {
            emit(starWarsApiService.getPeople())
        }
    }

}