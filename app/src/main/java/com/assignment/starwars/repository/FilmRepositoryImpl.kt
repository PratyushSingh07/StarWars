package com.assignment.starwars.repository

import com.assignment.starwars.api.StarWarsApiService
import com.assignment.starwars.models.FilmResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FilmRepositoryImpl @Inject constructor(private val starWarsApiService: StarWarsApiService) :
    FilmRepository {

    override suspend fun getFilms(filmUrl: String): Flow<FilmResponse> {
        return flow {
            emit(starWarsApiService.getFilms(filmUrl))
        }
    }
}