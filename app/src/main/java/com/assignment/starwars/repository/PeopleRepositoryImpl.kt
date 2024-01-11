package com.assignment.starwars.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.assignment.starwars.api.StarWarsApiService
import com.assignment.starwars.db.dao.PersonDao
import com.assignment.starwars.models.Person
import com.assignment.starwars.models.PersonDB
import com.assignment.starwars.utils.PeoplePagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PeopleRepositoryImpl @Inject constructor(
    private val starWarsApiService: StarWarsApiService,
    private val personDao: PersonDao
) :
    PeopleRepository {

    override suspend fun getPeople(): Flow<PagingData<Person>> {
        return Pager(
            config = PagingConfig(pageSize = 10, maxSize = 100),
            pagingSourceFactory = { PeoplePagingSource(starWarsApiService) }
        ).flow
    }

    override fun getOfflinePeople(): Flow<PagingData<PersonDB>> {
        return Pager(
            config = PagingConfig(pageSize = 10, maxSize = 100),
            pagingSourceFactory = { personDao.getPersonList() }
        ).flow
    }

    override suspend fun saveOffline(personDBList: List<PersonDB>) {
        return personDao.updatePersonList(personDBList)
    }


}