package com.assignment.starwars.api

import com.assignment.starwars.models.PeopleResponse
import com.assignment.starwars.models.Person
import com.assignment.starwars.models.Planet
import com.assignment.starwars.models.PlanetResponse
import com.assignment.starwars.models.StarShipResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface StarWarsApiService {

    @GET(ApiEndPoints.PEOPLE)
    suspend fun getPeople(@Query("page")page: Int): PeopleResponse

    @GET(ApiEndPoints.STARSHIPS)
    suspend fun getStarships(): StarShipResponse

    @GET(ApiEndPoints.PLANETS)
    suspend fun getPlanets(): PlanetResponse

    @GET("{personUrl}")
    suspend fun getPerson(@Path("personUrl") personUrl: String?): Person

    @GET("{planetUrl}")
    suspend fun getPlanet(@Path("planetUrl") planetUrl: String): Planet
}