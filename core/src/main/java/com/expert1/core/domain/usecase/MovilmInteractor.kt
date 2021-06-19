package com.expert1.core.domain.usecase

import com.expert1.core.domain.model.Movie
import com.expert1.core.domain.model.TvShow
import com.expert1.core.domain.repository.IMovilmRepository

class MovilmInteractor(private val movilmRepository: IMovilmRepository): MovilmUseCase {
    override fun getMovie() = movilmRepository.getMovie()

    override fun getMovieDetail(id: Int) = movilmRepository.getMovieDetail(id)

    override fun getListFavMovie() = movilmRepository.getListFavMovie()

    override fun setFavMovie(movie: Movie, state: Boolean) = movilmRepository.setFavMovie(movie, state)

    override fun getTV() = movilmRepository.getTV()

    override fun getTVDetail(id: Int) = movilmRepository.getTVDetail(id)

    override fun getListFavTV() = movilmRepository.getListFavTV()

    override fun setFavTV(tv: TvShow, state: Boolean) = movilmRepository.setFavTV(tv, state)

}