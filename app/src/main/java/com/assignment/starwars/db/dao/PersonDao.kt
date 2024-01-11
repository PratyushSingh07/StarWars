package com.assignment.starwars.db.dao

import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.assignment.starwars.models.PersonDB
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonDao {

    @Upsert
    suspend fun updatePersonList(personDBList: List<PersonDB>)

    @Query("SELECT * FROM person")
    fun getPersonList(): PagingSource<Int,PersonDB>

}