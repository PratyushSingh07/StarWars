package com.assignment.starwars.ui.filter

interface SortOptionListener {
    fun onSortOptionSelected(option: SortOption)
}

enum class SortOption {
    NAME, MALE, FEMALE
}
