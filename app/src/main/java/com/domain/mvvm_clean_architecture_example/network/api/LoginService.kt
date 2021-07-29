package com.domain.mvvm_clean_architecture_example.network.api

import com.domain.mvvm_clean_architecture_example.domain.login.model.request.LoginRequest
import com.domain.mvvm_clean_architecture_example.domain.login.model.response.LoginModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {

    @POST("login")
    suspend fun doLogin(@Body loginRequest: LoginRequest): Response<LoginModel>
}