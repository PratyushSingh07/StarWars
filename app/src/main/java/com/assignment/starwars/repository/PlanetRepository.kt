package com.assignment.starwars.repository

import com.assignment.starwars.models.PlanetResponse
import kotlinx.coroutines.flow.Flow

interface PlanetRepository {

    fun getPlanets(): Flow<PlanetResponse>

}