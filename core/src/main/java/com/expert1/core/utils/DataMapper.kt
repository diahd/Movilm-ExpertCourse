package com.expert1.core.utils

import com.expert1.core.data.source.local.entity.MovieEntity
import com.expert1.core.data.source.local.entity.TvShowEntity
import com.expert1.core.data.source.remote.response.ResultsItem
import com.expert1.core.data.source.remote.response.ResultsItemTV
import com.expert1.core.domain.model.Movie
import com.expert1.core.domain.model.TvShow

object DataMapper {
    fun mapResponsesToEntitiesMovie(input: List<ResultsItem>) : List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                    movieId = null,
                    poster = it.posterPath,
                    id = it.id,
                    title = it.title,
                    date = it.releaseDate,
                    score = it.voteAverage.toString(),
                    overview = it.overview,
                    isFavorite = false
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapResponsesToEntitiesDetailMovie(input: ResultsItem) : MovieEntity =
        MovieEntity(
                null,
                input.posterPath,
                input.id,
                input.title,
                input.releaseDate,
                input.voteAverage.toString(),
                input.overview,
                false
        )

    fun mapEntitiesToDomainMovie(input: List<MovieEntity>) : List<Movie> =
            input.map {
                Movie(
                        movieId = it.movieId,
                        poster = it.poster,
                        id = it.id,
                        title = it.title,
                        date = it.date,
                        score = it.score,
                        overview = it.overview,
                        isFavorite = it.isFavorite
                )
            }

    fun mapEntitiesToDomainDetailMovie(input: MovieEntity) : Movie =
            Movie(
                    movieId = input.movieId,
                    poster = input.poster,
                    id = input.id,
                    title = input.title,
                    date = input.date,
                    score = input.score,
                    overview = input.overview,
                    isFavorite = input.isFavorite
            )

    fun mapDomainToEntityMovie(input: Movie) = MovieEntity(
            movieId = input.movieId,
            poster = input.poster,
            id = input.id,
            title = input.title,
            date = input.date,
            score = input.score,
            overview = input.overview,
            isFavorite = input.isFavorite
    )

    fun mapResponsesToEntitiesTV(input: List<ResultsItemTV>) : List<TvShowEntity> {
        val tvList = ArrayList<TvShowEntity>()
        input.map {
            val tv = TvShowEntity(
                    tvId = null,
                    poster = it.posterPath,
                    id = it.id,
                    title = it.name,
                    date = it.firstAirDate,
                    score = it.voteAverage.toString(),
                    overview = it.overview,
                    isFavorite = false
            )
            tvList.add(tv)
        }
        return tvList
    }

    fun mapResponsesToEntitiesDetailTv(input: ResultsItemTV) : TvShowEntity =
            TvShowEntity(
                    null,
                    input.posterPath,
                    input.id,
                    input.name,
                    input.firstAirDate,
                    input.voteAverage.toString(),
                    input.overview,
                    false
            )

    fun mapEntitiesToDomainTV(input: List<TvShowEntity>) : List<TvShow> =
            input.map {
                TvShow(
                        tvId = it.tvId,
                        poster = it.poster,
                        id = it.id,
                        title = it.title,
                        date = it.date,
                        score = it.score,
                        overview = it.overview,
                        isFavorite = it.isFavorite
                )
            }

    fun mapEntitiesToDomainDetailTv(input: TvShowEntity) : TvShow =
            TvShow(
                    tvId = input.tvId,
                    poster = input.poster,
                    id = input.id,
                    title = input.title,
                    date = input.date,
                    score = input.score,
                    overview = input.overview,
                    isFavorite = input.isFavorite
            )

    fun mapDomainToEntityTV(input: TvShow) = TvShowEntity(
            tvId = input.tvId,
            poster = input.poster,
            id = input.id,
            title = input.title,
            date = input.date,
            score = input.score,
            overview = input.overview,
            isFavorite = input.isFavorite
    )
}