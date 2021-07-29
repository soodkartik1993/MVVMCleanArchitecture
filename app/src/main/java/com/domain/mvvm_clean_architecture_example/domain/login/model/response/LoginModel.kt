package com.domain.mvvm_clean_architecture_example.domain.login.model.response

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class LoginModel(
    @PrimaryKey val id: Long,
    val email: String,
    val password: String
)