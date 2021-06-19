package com.expert1.core.domain.repository

import com.expert1.core.data.source.Resource
import com.expert1.core.domain.model.Movie
import com.expert1.core.domain.model.TvShow
import kotlinx.coroutines.flow.Flow

interface IMovilmRepository {
    //movie
    fun getMovie(): Flow<Resource<List<Movie>>>

    fun getMovieDetail(id : Int) : Flow<Resource<Movie>>

    fun getListFavMovie(): Flow<List<Movie>>

    fun setFavMovie(movie: Movie, state: Boolean)

    //TV
    fun getTV(): Flow<Resource<List<TvShow>>>

    fun getTVDetail(id : Int) : Flow<Resource<TvShow>>

    fun getListFavTV(): Flow<List<TvShow>>

    fun setFavTV(tv: TvShow, state: Boolean)
}