package com.assignment.starwars.models

data class StarShipResponse(
    val count: Int,
    val next: String,
    val previous: String,
    val results: MutableList<Starship>
)
