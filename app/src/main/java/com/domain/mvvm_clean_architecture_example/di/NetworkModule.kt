package com.domain.mvvm_clean_architecture_example.di

import com.domain.mvvm_clean_architecture_example.BuildConfig
import com.domain.mvvm_clean_architecture_example.constant.Constants
import com.domain.mvvm_clean_architecture_example.constant.Enums
import com.domain.mvvm_clean_architecture_example.domain.login.datasource.LoginDataSource
import com.domain.mvvm_clean_architecture_example.network.RequestInterceptor
import com.domain.mvvm_clean_architecture_example.network.api.LoginService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.component.KoinComponent
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkManager : KoinComponent {
    val NetworkModule = module {
        val sTime = 120

        single { GsonConverterFactory.create() as Converter.Factory }
        single { HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY) }

        single {
            OkHttpClient.Builder()
                .connectTimeout(sTime.toLong(), TimeUnit.SECONDS)
                .readTimeout(sTime.toLong(), TimeUnit.SECONDS)
                .writeTimeout(sTime.toLong(), TimeUnit.SECONDS)
                .addInterceptor { chain ->
                    RequestInterceptor(get(), getBearerToken(get())).intercept(chain)
                }
                .build()
        }

        single {
            Retrofit.Builder()
                .client(get())
                .baseUrl(BuildConfig.BASEURL)
                .addConverterFactory(get())
                .build()
        }

        single { createLoginService(get()) }
        single { LoginDataSource(get()) }
    }

    private fun createLoginService(retrofit: Retrofit): LoginService {
        return retrofit.create(LoginService::class.java)
    }

    private fun getBearerToken(preferencesManager: PreferencesManager): String? {
        return preferencesManager.getData(
            Enums.SharePreferencesEnum.StringType.name,
            Constants.SharedPreferences.sSESSION_TOKEN
        ) as? String?
    }
}