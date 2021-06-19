package com.expert1.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TvShow (
        var tvId: Int? = null,
        var poster: String? = null,
        var id: Int? = null,
        var title: String? = null,
        var date: String? = null,
        var score: String? = null,
        var overview: String? = null,
        var isFavorite: Boolean = false
): Parcelable