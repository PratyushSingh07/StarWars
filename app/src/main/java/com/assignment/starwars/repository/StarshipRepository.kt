package com.assignment.starwars.repository

import com.assignment.starwars.models.StarShipResponse
import kotlinx.coroutines.flow.Flow

interface StarshipRepository {

    fun getStarships(): Flow<StarShipResponse>

}