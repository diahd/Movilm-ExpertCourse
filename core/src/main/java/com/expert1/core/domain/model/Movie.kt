package com.expert1.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie (
        var movieId: Int?,
        var poster: String?,
        var id: Int?,
        var title: String?,
        var date: String?,
        var score: String?,
        var overview: String?,
        var isFavorite: Boolean = false
): Parcelable