package com.domain.mvvm_clean_architecture_example.domain.login.viewmodel

import androidx.lifecycle.viewModelScope
import com.domain.mvvm_clean_architecture_example.base.BaseViewModel
import com.domain.mvvm_clean_architecture_example.domain.login.model.request.LoginRequest
import com.domain.mvvm_clean_architecture_example.domain.login.repository.LoginRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class LoginViewModel(private val loginRepository: LoginRepository) : BaseViewModel() {
    private val _loginRequest = MutableStateFlow(LoginRequest())
    val loginRequest: StateFlow<LoginRequest> = _loginRequest

    fun doLogin() {
        viewModelScope.launch {
            loginRepository.doLogin(loginRequest.value)
        }
    }
}