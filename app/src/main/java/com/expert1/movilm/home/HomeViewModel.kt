package com.expert1.movilm.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.expert1.core.domain.usecase.MovilmUseCase

class HomeViewModel(private val movilmUseCase: MovilmUseCase) : ViewModel() {

    fun getMovies() = movilmUseCase.getMovie().asLiveData()
    fun getTv() = movilmUseCase.getTV().asLiveData()
}