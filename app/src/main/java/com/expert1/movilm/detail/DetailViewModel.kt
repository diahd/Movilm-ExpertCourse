package com.expert1.movilm.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.expert1.core.domain.model.Movie
import com.expert1.core.domain.model.TvShow
import com.expert1.core.domain.usecase.MovilmUseCase

class DetailViewModel(private val movilmUseCase: MovilmUseCase): ViewModel() {

    fun getMovieDetail(id : Int) = movilmUseCase.getMovieDetail(id).asLiveData()

    fun getTvDetail(id: Int) = movilmUseCase.getTVDetail(id).asLiveData()

    fun setFavMovie(movie: Movie, state: Boolean) = movilmUseCase.setFavMovie(movie, state)

    fun setFavTV(tv: TvShow, state: Boolean) = movilmUseCase.setFavTV(tv, state)
}