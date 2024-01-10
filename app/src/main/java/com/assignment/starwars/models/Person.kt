package com.assignment.starwars.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Person(
    val birth_year: String,
    val created: String,
    val edited: String,
    val eye_color: String,
    val films: ArrayList<String>,
    val gender: String,
    val hair_color: String,
    val height: String,
    val homeworld: String,
    val mass: String,
    val name: String,
    val skin_color: String,
    val species: List<String>,
    val starships: List<String>,
    val url: String,
    val vehicles: List<String>
) : Parcelable