package com.assignment.starwars.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.assignment.starwars.api.StarWarsApiService
import com.assignment.starwars.models.Person
import com.assignment.starwars.utils.PeoplePagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PeopleRepositoryImpl @Inject constructor(private val starWarsApiService: StarWarsApiService) :
    PeopleRepository {

    override suspend fun getPeople(): Flow<PagingData<Person>> {
        return Pager(
            config = PagingConfig(pageSize = 10, maxSize = 100),
            pagingSourceFactory = { PeoplePagingSource(starWarsApiService) }
        ).flow
    }

}