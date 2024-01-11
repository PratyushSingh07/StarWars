package com.assignment.starwars.di

import android.content.Context
import androidx.room.Room
import com.assignment.starwars.db.PersonDatabase
import com.assignment.starwars.db.dao.PersonDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providesPersonDatabase(@ApplicationContext context: Context): PersonDatabase {
        return Room.databaseBuilder(context, PersonDatabase::class.java, "StarWars").build()
    }

    @Provides
    @Singleton
    fun providesPersonDao(personDatabase: PersonDatabase): PersonDao {
        return personDatabase.personDao()
    }
}