package com.assignment.starwars.repository

import com.assignment.starwars.api.StarWarsApiService
import com.assignment.starwars.models.PlanetResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PlanetRepositoryImpl @Inject constructor(private val starWarsApiService: StarWarsApiService) :
    PlanetRepository {

    override fun getPlanets(): Flow<PlanetResponse> {
        return flow {
            emit(starWarsApiService.getPlanets())
        }
    }

}