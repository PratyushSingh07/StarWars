package com.assignment.starwars.repository

import com.assignment.starwars.models.FilmResponse
import kotlinx.coroutines.flow.Flow

interface FilmRepository {

    suspend fun getFilms(filmUrl: String): Flow<FilmResponse>

}