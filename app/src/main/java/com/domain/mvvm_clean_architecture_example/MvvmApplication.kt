package com.domain.mvvm_clean_architecture_example

import android.app.Application
import com.domain.mvvm_clean_architecture_example.di.NetworkManager.NetworkModule
import com.domain.mvvm_clean_architecture_example.di.PersistenceModule
import com.domain.mvvm_clean_architecture_example.di.PreferencesManager.PreferencesModule
import com.domain.mvvm_clean_architecture_example.di.RepositoryModule
import com.domain.mvvm_clean_architecture_example.di.ViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class MvvmApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MvvmApplication)
            modules(
                listOf(
                    PreferencesModule,
                    NetworkModule,
                    PersistenceModule,
                    RepositoryModule,
                    ViewModelModule
                )
            )
        }
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}