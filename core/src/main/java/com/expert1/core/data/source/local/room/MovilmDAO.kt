package com.expert1.core.data.source.local.room

import androidx.room.*
import com.expert1.core.data.source.local.entity.MovieEntity
import com.expert1.core.data.source.local.entity.TvShowEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovilmDAO {
    //Movie
    @Query("SELECT * FROM movieentity")
    fun getMovie(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movieentity WHERE isFavorite = 1")
    fun getListFavMovie(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movieentity WHERE id = :movieId")
    fun getDetailMovie(movieId: Int) : Flow<MovieEntity>

    //TV
    @Query("SELECT * FROM tvshowentity")
    fun getTV(): Flow<List<TvShowEntity>>

    @Query("SELECT * FROM tvshowentity WHERE isFavorite = 1")
    fun getListFavTV(): Flow<List<TvShowEntity>>

    @Query("SELECT * FROM tvshowentity WHERE id = :tvId")
    fun getDetailTV(tvId: Int) : Flow<TvShowEntity>

    //Insert
    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = MovieEntity::class)
    suspend fun insertMovie(movies: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = MovieEntity::class)
    suspend fun insertMovieDetail(movie: MovieEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = TvShowEntity::class)
    suspend fun insertTV(tv: List<TvShowEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = TvShowEntity::class)
    suspend fun insertTVDetail(tv: TvShowEntity)

    //Update
    @Update(entity = MovieEntity::class)
    fun updateMovie(movie: MovieEntity)

    @Update(entity = TvShowEntity::class)
    fun updateTV(tv: TvShowEntity)
}