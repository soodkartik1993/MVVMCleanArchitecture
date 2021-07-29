package com.domain.mvvm_clean_architecture_example.domain.login.datasource

import com.domain.mvvm_clean_architecture_example.domain.login.model.request.LoginRequest
import com.domain.mvvm_clean_architecture_example.network.api.LoginService

class LoginDataSource(private val loginService: LoginService) {

    suspend fun doLogin(loginRequest: LoginRequest) {
        loginService.doLogin(loginRequest)
    }
}