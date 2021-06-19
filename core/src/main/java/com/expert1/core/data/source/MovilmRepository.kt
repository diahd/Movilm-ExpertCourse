package com.expert1.core.data.source

import com.expert1.core.data.source.local.LocalDataSource
import com.expert1.core.data.source.remote.RemoteDataSource
import com.expert1.core.data.source.remote.api.ApiResponse
import com.expert1.core.data.source.remote.response.ResultsItem
import com.expert1.core.data.source.remote.response.ResultsItemTV
import com.expert1.core.domain.model.Movie
import com.expert1.core.domain.model.TvShow
import com.expert1.core.domain.repository.IMovilmRepository
import com.expert1.core.utils.AppExecutors
import com.expert1.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovilmRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IMovilmRepository {

    override fun getMovie(): Flow<Resource<List<Movie>>> {
        return object : NetworkBoundResource<List<Movie>, List<ResultsItem>>(appExecutors){
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getAllMovies().map {
                    DataMapper.mapEntitiesToDomainMovie(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<ResultsItem>>> =
                remoteDataSource.getMovie()

            override suspend fun saveCallResult(data: List<ResultsItem>) {
                val movieList = DataMapper.mapResponsesToEntitiesMovie(data)
                localDataSource.insertMovies(movieList)
            }
        }.asFlow()
    }

    override fun getMovieDetail(id: Int): Flow<Resource<Movie>> {
        return object : NetworkBoundResource<Movie, ResultsItem>(appExecutors){
            override fun loadFromDB(): Flow<Movie> =
                localDataSource.getDetailMovies(id).map {
                    DataMapper.mapEntitiesToDomainDetailMovie(it)
                }

            override fun shouldFetch(data: Movie?): Boolean =
                data == null

            public override suspend fun createCall(): Flow<ApiResponse<ResultsItem>> =
                remoteDataSource.getMovieDetail(id)

            override suspend fun saveCallResult(data: ResultsItem) {
                val movie = DataMapper.mapResponsesToEntitiesDetailMovie(data)
                localDataSource.insertMovieDetail(movie)
            }
        }.asFlow()
    }

    override fun getTV(): Flow<Resource<List<TvShow>>> {
        return object : NetworkBoundResource<List<TvShow>, List<ResultsItemTV>>(appExecutors){
            override fun loadFromDB(): Flow<List<TvShow>> {
                return localDataSource.getAllTV().map {
                    DataMapper.mapEntitiesToDomainTV(it)
                }
            }

            override fun shouldFetch(data: List<TvShow>?): Boolean =
                data == null || data.isEmpty()

            public override suspend fun createCall(): Flow<ApiResponse<List<ResultsItemTV>>> =
                remoteDataSource.getTV()

            public override suspend fun saveCallResult(data: List<ResultsItemTV>) {
                val tvList = DataMapper.mapResponsesToEntitiesTV(data)
                localDataSource.insertTV(tvList)
            }
        }.asFlow()
    }

    override fun getTVDetail(id: Int): Flow<Resource<TvShow>> {
        return object : NetworkBoundResource<TvShow, ResultsItemTV>(appExecutors){
            override fun loadFromDB(): Flow<TvShow> =
                localDataSource.getDetailTVs(id).map {
                    DataMapper.mapEntitiesToDomainDetailTv(it)
                }

            override fun shouldFetch(data: TvShow?): Boolean =
                data == null

            public override suspend fun createCall(): Flow<ApiResponse<ResultsItemTV>> =
                remoteDataSource.getTVDetail(id)

            override suspend fun saveCallResult(data: ResultsItemTV) {
                val tv = DataMapper.mapResponsesToEntitiesDetailTv(data)
                localDataSource.insertTVDetail(tv)
            }
        }.asFlow()
    }

    override fun getListFavMovie(): Flow<List<Movie>> {
        return localDataSource.getListFavMovies().map {
            DataMapper.mapEntitiesToDomainMovie(it)
        }
    }

    override fun setFavMovie(movie: Movie, state: Boolean) {
        val movieEntity =  DataMapper.mapDomainToEntityMovie(movie)
        appExecutors.diskIO().execute { localDataSource.setFavMovie(movieEntity, state) }
    }

    override fun getListFavTV(): Flow<List<TvShow>> {
        return localDataSource.getListFavTVs().map {
            DataMapper.mapEntitiesToDomainTV(it)
        }
    }

    override fun setFavTV(tv: TvShow, state: Boolean) {
        val tvEntity =  DataMapper.mapDomainToEntityTV(tv)
        appExecutors.diskIO().execute { localDataSource.setFavTV(tvEntity, state) }
    }
}