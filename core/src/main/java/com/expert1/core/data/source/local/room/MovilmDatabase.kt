package com.expert1.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.expert1.core.data.source.local.entity.MovieEntity
import com.expert1.core.data.source.local.entity.TvShowEntity

@Database(entities = [MovieEntity::class, TvShowEntity::class],
    version = 1,
    exportSchema = false)
abstract class MovilmDatabase: RoomDatabase() {
    abstract fun movilmDao(): MovilmDAO
}