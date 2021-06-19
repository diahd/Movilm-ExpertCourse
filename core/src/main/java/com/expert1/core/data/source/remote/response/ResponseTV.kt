package com.expert1.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ResponseTV(

    @field:SerializedName("results")
    val resultsTV: List<ResultsItemTV>,
)

data class ResultsItemTV(

    @field:SerializedName("first_air_date")
    val firstAirDate: String,

    @field:SerializedName("overview")
    val overview: String,

    @field:SerializedName("poster_path")
    val posterPath: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("vote_average")
    val voteAverage: Double,

    @field:SerializedName("id")
    val id: Int
)