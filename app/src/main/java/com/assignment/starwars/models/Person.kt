package com.assignment.starwars.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Person(
    val birth_year: String,
    val created: String,
    val edited: String,
    var films: ArrayList<String>? = null,
    val gender: String,
    val height: String,
    val homeworld: String,
    val mass: String,
    val name: String,
    val starships: List<String>? = null,
    val vehicles: List<String>? = null
) : Parcelable