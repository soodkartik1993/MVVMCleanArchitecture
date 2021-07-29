package com.domain.mvvm_clean_architecture_example.base

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {
    val mIsLoading = ObservableBoolean()

    fun getLoadingObservable(): ObservableBoolean {
        return mIsLoading
    }

    override fun onCleared() {
        super.onCleared()
    }
}