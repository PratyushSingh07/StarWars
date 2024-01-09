package com.assignment.starwars.models

data class PeopleResponse(
    val count: Int,
    val next: String,
    val previous: String,
    val results: MutableList<Person>
)
