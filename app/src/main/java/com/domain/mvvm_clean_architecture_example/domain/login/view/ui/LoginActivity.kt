package com.domain.mvvm_clean_architecture_example.domain.login.view.ui

import android.os.Bundle
import com.domain.mvvm_clean_architecture_example.R
import com.domain.mvvm_clean_architecture_example.base.BaseActivity
import com.domain.mvvm_clean_architecture_example.databinding.ActivityLoginBinding
import com.domain.mvvm_clean_architecture_example.domain.login.viewmodel.LoginViewModel
import org.koin.android.ext.android.get

class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>() {
    private val loginViewModel = get<LoginViewModel>()
    override fun getLayoutId(): Int = R.layout.activity_login
    override fun getViewModel(): LoginViewModel = loginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getDataBinding().loginviewmodel = loginViewModel
    }
}