package com.expert1.core.data.source.local

import com.expert1.core.data.source.local.entity.MovieEntity
import com.expert1.core.data.source.local.entity.TvShowEntity
import com.expert1.core.data.source.local.room.MovilmDAO
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val mMovilmDAO: MovilmDAO) {

    fun getAllMovies(): Flow<List<MovieEntity>> = mMovilmDAO.getMovie()

    fun getListFavMovies() : Flow<List<MovieEntity>> = mMovilmDAO.getListFavMovie()

    fun getDetailMovies(movieId: Int) : Flow<MovieEntity> = mMovilmDAO.getDetailMovie(movieId)

    suspend fun insertMovies(movies: List<MovieEntity>) = mMovilmDAO.insertMovie(movies)

    suspend fun insertMovieDetail(movie: MovieEntity) = mMovilmDAO.insertMovieDetail(movie)

    fun setFavMovie(movie : MovieEntity, state: Boolean){
        movie.isFavorite = !state
        mMovilmDAO.updateMovie(movie)
    }

    //tv
    fun getAllTV(): Flow<List<TvShowEntity>> = mMovilmDAO.getTV()

    fun getListFavTVs(): Flow<List<TvShowEntity>> = mMovilmDAO.getListFavTV()

    fun getDetailTVs(tvId: Int) : Flow<TvShowEntity> = mMovilmDAO.getDetailTV(tvId)

    suspend fun insertTV(tvs: List<TvShowEntity>) = mMovilmDAO.insertTV(tvs)

    suspend fun insertTVDetail(tv: TvShowEntity) = mMovilmDAO.insertTVDetail(tv)

    fun setFavTV(tv : TvShowEntity, state: Boolean) {
        tv.isFavorite = !state
        mMovilmDAO.updateTV(tv)
    }
}