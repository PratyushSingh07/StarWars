package com.assignment.starwars.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "person")
data class PersonDB(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,

    val birth_year: String,
    val created: String,
    val edited: String,
    val gender: String,
    val height: String,
    val homeworld: String,
    val mass: String,
    val name: String,
) : Parcelable
