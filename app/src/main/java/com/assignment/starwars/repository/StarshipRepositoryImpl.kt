package com.assignment.starwars.repository

import com.assignment.starwars.api.StarWarsApiService
import com.assignment.starwars.models.StarShipResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class StarshipRepositoryImpl @Inject constructor(private val starWarsApiService: StarWarsApiService) :
    StarshipRepository {

    override fun getStarships(): Flow<StarShipResponse> {
        return flow {
            emit(starWarsApiService.getStarships())
        }
    }

}