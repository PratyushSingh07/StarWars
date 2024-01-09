package com.assignment.starwars.models

data class PlanetResponse(
    val count: Int,
    val next: String,
    val previous: String,
    val results: MutableList<Planet>
)
