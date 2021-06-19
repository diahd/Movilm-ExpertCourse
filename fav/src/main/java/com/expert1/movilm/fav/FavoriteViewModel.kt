package com.expert1.movilm.fav

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.expert1.core.domain.model.Movie
import com.expert1.core.domain.model.TvShow
import com.expert1.core.domain.usecase.MovilmUseCase

class FavoriteViewModel(private val movilmUseCase: MovilmUseCase) : ViewModel()  {

    fun getFavMovie() = movilmUseCase.getListFavMovie().asLiveData()

    fun getFavTV() = movilmUseCase.getListFavTV().asLiveData()

    fun setFavMovie(movieEntity: Movie, newState: Boolean) {
        movilmUseCase.setFavMovie(movieEntity, newState)
    }

    fun setFavTV(tvEntity: TvShow, newState: Boolean) {
        movilmUseCase.setFavTV(tvEntity, newState)
    }
}