package com.domain.mvvm_clean_architecture_example.di

import com.domain.mvvm_clean_architecture_example.domain.login.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ViewModelModule = module {
    viewModel { LoginViewModel(get()) }
}