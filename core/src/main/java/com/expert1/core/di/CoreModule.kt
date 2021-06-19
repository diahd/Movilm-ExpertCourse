package com.expert1.core.di

import androidx.room.Room
import com.expert1.core.BuildConfig
import com.expert1.core.data.source.MovilmRepository
import com.expert1.core.data.source.local.LocalDataSource
import com.expert1.core.data.source.local.room.MovilmDatabase
import com.expert1.core.data.source.remote.RemoteDataSource
import com.expert1.core.data.source.remote.api.ApiService
import com.expert1.core.domain.repository.IMovilmRepository
import com.expert1.core.utils.AppExecutors
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val databaseModule = module {
    factory { get<MovilmDatabase>().movilmDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            MovilmDatabase::class.java, "database.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IMovilmRepository> { MovilmRepository(get(), get(), get())}
}