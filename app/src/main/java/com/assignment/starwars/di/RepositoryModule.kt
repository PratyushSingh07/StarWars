package com.assignment.starwars.di

import com.assignment.starwars.api.StarWarsApiService
import com.assignment.starwars.db.PersonDatabase
import com.assignment.starwars.db.dao.PersonDao
import com.assignment.starwars.repository.FilmRepository
import com.assignment.starwars.repository.FilmRepositoryImpl
import com.assignment.starwars.repository.PeopleRepository
import com.assignment.starwars.repository.PeopleRepositoryImpl
import com.assignment.starwars.repository.PlanetRepository
import com.assignment.starwars.repository.PlanetRepositoryImpl
import com.assignment.starwars.repository.StarshipRepository
import com.assignment.starwars.repository.StarshipRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providesPeopleRepository(
        starWarsApiService: StarWarsApiService,
        personDao: PersonDao
    ): PeopleRepository {
        return PeopleRepositoryImpl(starWarsApiService, personDao)
    }

    @Provides
    @Singleton
    fun providesStarshipRepository(starWarsApiService: StarWarsApiService): StarshipRepository {
        return StarshipRepositoryImpl(starWarsApiService)
    }

    @Provides
    @Singleton
    fun providesPlanetRepository(starWarsApiService: StarWarsApiService): PlanetRepository {
        return PlanetRepositoryImpl(starWarsApiService)
    }

    @Provides
    @Singleton
    fun providesFilmRepository(starWarsApiService: StarWarsApiService): FilmRepository {
        return FilmRepositoryImpl(starWarsApiService)
    }
}