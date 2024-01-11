package com.assignment.starwars.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.assignment.starwars.db.dao.PersonDao
import com.assignment.starwars.models.PersonDB

@Database(entities = [PersonDB::class], version = 2)
abstract class PersonDatabase: RoomDatabase() {

    abstract fun personDao(): PersonDao

}