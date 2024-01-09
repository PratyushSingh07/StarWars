package com.assignment.starwars.repository

import androidx.paging.PagingData
import com.assignment.starwars.models.Person
import kotlinx.coroutines.flow.Flow

interface PeopleRepository {

    suspend fun getPeople(): Flow<PagingData<Person>>

}