package com.assignment.starwars.repository

import androidx.paging.PagingData
import com.assignment.starwars.models.Person
import com.assignment.starwars.models.PersonDB
import kotlinx.coroutines.flow.Flow

interface PeopleRepository {

    suspend fun getPeople(): Flow<PagingData<Person>>

    fun getOfflinePeople(): Flow<PagingData<PersonDB>>

    suspend fun saveOffline(personDBList: List<PersonDB>)
}