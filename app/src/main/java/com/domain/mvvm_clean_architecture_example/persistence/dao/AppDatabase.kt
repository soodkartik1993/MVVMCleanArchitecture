package com.domain.mvvm_clean_architecture_example.persistence.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.domain.mvvm_clean_architecture_example.domain.login.model.response.LoginModel

@Database(entities = [LoginModel::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun loginDao(): LoginDao
}