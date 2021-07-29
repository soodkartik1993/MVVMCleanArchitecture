package com.domain.mvvm_clean_architecture_example.di

import com.domain.mvvm_clean_architecture_example.domain.login.repository.LoginRepository
import org.koin.dsl.module

val RepositoryModule = module {
    single { LoginRepository(get(), get()) }
}
