package com.assignment.starwars.repository

import com.assignment.starwars.models.PeopleResponse
import kotlinx.coroutines.flow.Flow

interface PeopleRepository {

    fun getPeople(): Flow<PeopleResponse>

}