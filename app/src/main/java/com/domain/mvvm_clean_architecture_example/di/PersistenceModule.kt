package com.domain.mvvm_clean_architecture_example.di

import androidx.room.Room
import com.domain.mvvm_clean_architecture_example.BuildConfig
import com.domain.mvvm_clean_architecture_example.persistence.cache.Cache
import com.domain.mvvm_clean_architecture_example.persistence.dao.AppDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val PersistenceModule = module {

    single {
        Room
            .databaseBuilder(
                androidApplication(),
                AppDatabase::class.java,
                BuildConfig.database
            )
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    single { get<AppDatabase>().loginDao() }

    single { Cache(get()) }
}