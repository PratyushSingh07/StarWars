package com.assignment.starwars.utils

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.assignment.starwars.api.StarWarsApiService
import com.assignment.starwars.models.Person

class PeoplePagingSource(val starWarsApiService: StarWarsApiService) : PagingSource<Int, Person>() {

    override fun getRefreshKey(state: PagingState<Int, Person>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Person> {
        return try {
            val pos = params.key ?: 1
            val response = starWarsApiService.getPeople(pos)
            return LoadResult.Page(
                data = response.results,
                prevKey = if (pos == 1) null else pos - 1,
                nextKey = if (pos == 9) null else pos + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}