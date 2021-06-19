package com.expert1.core.data.source.remote.api

import com.expert1.core.BuildConfig
import com.expert1.core.data.source.remote.response.ResponseMovie
import com.expert1.core.data.source.remote.response.ResponseTV
import com.expert1.core.data.source.remote.response.ResultsItem
import com.expert1.core.data.source.remote.response.ResultsItemTV
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/upcoming")
    suspend fun getMovie(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): ResponseMovie

    @GET("movie/{id}")
    suspend fun getMovieDetail(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): ResultsItem

    @GET("tv/on_the_air")
    suspend fun getTV(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): ResponseTV

    @GET("tv/{id}")
    suspend fun getTVDetail(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): ResultsItemTV
}