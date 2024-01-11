package com.assignment.starwars.api

import com.assignment.starwars.models.FilmResponse
import com.assignment.starwars.models.PeopleResponse
import com.assignment.starwars.models.Person
import com.assignment.starwars.models.Planet
import com.assignment.starwars.models.PlanetResponse
import com.assignment.starwars.models.StarShipResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface StarWarsApiService {

    @GET(ApiEndPoints.PEOPLE)
    suspend fun getPeople(@Query("page") page: Int): PeopleResponse

    @GET(ApiEndPoints.STARSHIPS)
    suspend fun getStarships(): StarShipResponse

    @GET(ApiEndPoints.PLANETS)
    suspend fun getPlanets(): PlanetResponse

    @GET
    suspend fun getFilms(@Url filmUrl: String): FilmResponse
}