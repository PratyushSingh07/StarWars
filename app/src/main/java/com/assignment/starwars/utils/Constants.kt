package com.assignment.starwars.utils

import android.util.Log
import com.assignment.starwars.R
import com.assignment.starwars.models.Person
import com.assignment.starwars.models.PersonDB

object Constants {
    const val BASE_URL = "https://swapi.dev/api/"
    val STAR_WARS_IAMGES = intArrayOf(
        R.drawable.luke,
        R.drawable.darth_vader,
        R.drawable.millanium_falcon,
        R.drawable.x_wing,
        R.drawable.death_star,
        R.drawable.c3po,
        R.drawable.yoda,
        R.drawable.r2d2,
        R.drawable.star_destroyer,
        R.drawable.star_wars,
        R.drawable.tatooine,
        R.drawable.phantom_manace
    )

    fun personDBtoPerson(personDB: PersonDB): Person {
        return Person(
            height = personDB.height,
            homeworld = personDB.homeworld,
            mass = personDB.mass,
            name = personDB.name,
            gender = personDB.gender,
            birth_year = personDB.birth_year,
            edited = personDB.edited,
            created = personDB.created
        )
    }

    fun personToPersonDB(list: List<Person>): List<PersonDB> {
        val newArray = ArrayList<PersonDB>()
        list.forEach {
            newArray.add(
                PersonDB(
                    height = it.height,
                    homeworld = it.homeworld,
                    mass = it.mass,
                    name = it.name,
                    gender = it.gender,
//                    films = it.films
                    birth_year = it.birth_year,
                    edited = it.edited,
                    created = it.created
                )
            )
        }
        return newArray
    }
}