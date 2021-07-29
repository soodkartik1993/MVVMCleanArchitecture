package com.domain.mvvm_clean_architecture_example.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import androidx.databinding.ObservableBoolean
import androidx.databinding.ViewDataBinding
import com.domain.mvvm_clean_architecture_example.utils.ProgressDialogUtility

abstract class BaseActivity<T : ViewDataBinding, V : BaseViewModel> : AppCompatActivity() {

    private var mViewDataBinding: T? = null
    private var mViewModel: V? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDataBinding()
        performViewModel()
        addLoadingPropertyChangedCallback()
    }

    @LayoutRes
    abstract fun getLayoutId(): Int
    abstract fun getViewModel(): V

    private fun performDataBinding() {
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        mViewDataBinding?.run {
            lifecycleOwner = this@BaseActivity
            executePendingBindings()
        }
    }

    private fun performViewModel() {
        mViewModel = getViewModel()
    }

    fun getDataBinding(): T {
        return mViewDataBinding ?: throw DataBindingException("data binding exception found")
    }

    private fun addLoadingPropertyChangedCallback() {
        val callback = object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                if (getIsLoading()?.get() == true) {
                    ProgressDialogUtility.showProgressDialog(this@BaseActivity)
                    mViewModel?.mIsLoading?.removeOnPropertyChangedCallback(this)
                } else {
                    ProgressDialogUtility.hideProgressDialog()
                    mViewModel?.mIsLoading?.removeOnPropertyChangedCallback(this)
                }
            }
        }
        mViewModel?.mIsLoading?.addOnPropertyChangedCallback(callback)
    }

    private fun getIsLoading(): ObservableBoolean? {
        return mViewModel?.mIsLoading
    }

    class DataBindingException(message: String?) : Exception(message)
}