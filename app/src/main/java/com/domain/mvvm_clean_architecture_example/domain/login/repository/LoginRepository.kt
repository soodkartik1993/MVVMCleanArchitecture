package com.domain.mvvm_clean_architecture_example.domain.login.repository

import com.domain.mvvm_clean_architecture_example.domain.login.datasource.LoginDataSource
import com.domain.mvvm_clean_architecture_example.domain.login.model.request.LoginRequest
import com.domain.mvvm_clean_architecture_example.persistence.dao.LoginDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginRepository constructor(
    private val loginDataSource: LoginDataSource,
    private val loginDao: LoginDao
) {

    suspend fun doLogin(loginRequest: LoginRequest) = withContext(Dispatchers.IO) {
        loginDataSource.doLogin(loginRequest)
    }
}