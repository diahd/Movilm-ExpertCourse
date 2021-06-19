package com.expert1.movilm.fav.di

import com.expert1.movilm.fav.FavoriteViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mapsModule = module {
    viewModel { FavoriteViewModel(get()) }
}