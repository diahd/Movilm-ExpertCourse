package com.expert1.movilm.di

import com.expert1.core.domain.usecase.MovilmInteractor
import com.expert1.core.domain.usecase.MovilmUseCase
import com.expert1.movilm.detail.DetailViewModel
import com.expert1.movilm.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovilmUseCase> { MovilmInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}