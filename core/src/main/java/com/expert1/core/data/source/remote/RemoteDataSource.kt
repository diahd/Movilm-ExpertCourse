package com.expert1.core.data.source.remote

import com.expert1.core.data.source.remote.api.ApiResponse
import com.expert1.core.data.source.remote.api.ApiService
import com.expert1.core.data.source.remote.response.ResultsItem
import com.expert1.core.data.source.remote.response.ResultsItemTV
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService){

    suspend fun getMovie(): Flow<ApiResponse<List<ResultsItem>>> {
        return flow {
            try {
                val response = apiService.getMovie()
                val dataArray = response.results
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.error(e.toString()))
            }
        }.flowOn(IO)
    }

    suspend fun getMovieDetail(Id: Int): Flow<ApiResponse<ResultsItem>> {
        return flow {
            try {
                val response = apiService.getMovieDetail(Id)
                emit(ApiResponse.success(response))
            }catch (e: Exception) {
                emit(ApiResponse.error(e.toString()))
            }
        }.flowOn(IO)
    }

    suspend fun getTV(): Flow<ApiResponse<List<ResultsItemTV>>> {
        return flow {
            try {
                val response = apiService.getTV()
                val dataArray = response.resultsTV
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.success(response.resultsTV))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.error(e.toString()))
            }
        }.flowOn(IO)
    }

    fun getTVDetail(Id: Int): Flow<ApiResponse<ResultsItemTV>> {
        return flow {
            try {
                val response = apiService.getTVDetail(Id)
                emit(ApiResponse.success(response))
            }catch (e: Exception) {
                emit(ApiResponse.error(e.toString()))
            }
        }.flowOn(IO)
    }
}