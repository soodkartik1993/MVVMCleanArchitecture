package com.domain.mvvm_clean_architecture_example.di

import android.app.Application
import android.content.SharedPreferences
import com.domain.mvvm_clean_architecture_example.constant.Constants
import com.domain.mvvm_clean_architecture_example.constant.Enums
import org.koin.android.ext.koin.androidApplication
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.dsl.module

object PreferencesManager : KoinComponent {
    val PreferencesModule = module {
        single {
            getSharedPrefs(androidApplication())
        }

        single<SharedPreferences.Editor> {
            getSharedPrefs(androidApplication()).edit()
        }

        single {
            this@PreferencesManager
        }
    }

    private fun getSharedPrefs(androidApplication: Application): SharedPreferences {
        return androidApplication.getSharedPreferences(
            Constants.SharedPreferences.sSHARED_PREFERENCES,
            android.content.Context.MODE_PRIVATE
        )
    }

    private val mSharedPreferences: SharedPreferences by inject()

    /**
     * set data to sharedPreferences of any type
     * */
    private fun setData(
        pType: String,
        pSharedPrefString: String = Constants.sEmptyString,
        pValue: Any
    ) {
        val editor = mSharedPreferences.edit()
        when (pType) {
            Enums.SharePreferencesEnum.StringType.name -> editor.putString(
                pSharedPrefString,
                pValue as String?
            )
            Enums.SharePreferencesEnum.IntType.name -> editor.putInt(
                pSharedPrefString,
                pValue as Int
            )
            Enums.SharePreferencesEnum.BooleanType.name -> editor.putBoolean(
                pSharedPrefString,
                pValue as Boolean
            )
            Enums.SharePreferencesEnum.FloatType.name -> editor.putFloat(
                pSharedPrefString,
                pValue as Float
            )
            Enums.SharePreferencesEnum.LongType.name -> editor.putLong(
                pSharedPrefString,
                pValue as Long
            )
        }
        editor.apply()
    }

    /**
     * get data from sharedPreferences of any type
     * */
    fun getData(
        pType: String,
        pSharedPrefString: String = Constants.sEmptyString,
        pDefaultValue: Any? = null
    ): Any {
        val data = Constants.sEmptyString
        return when (pType) {
            Enums.SharePreferencesEnum.StringType.name -> mSharedPreferences.getString(
                pSharedPrefString,
                Constants.sEmptyString
            )!!
            Enums.SharePreferencesEnum.IntType.name -> {
                val defaultValue = pDefaultValue?.toString()?.toInt()
                    ?: Constants.SharedPreferences.sDEFAULT_INT_VALUE
                mSharedPreferences.getInt(pSharedPrefString, defaultValue)
            }
            Enums.SharePreferencesEnum.BooleanType.name -> mSharedPreferences.getBoolean(
                pSharedPrefString,
                Constants.SharedPreferences.sDEFAULT_BOOLEAN_VALUE
            )
            Enums.SharePreferencesEnum.FloatType.name -> mSharedPreferences.getFloat(
                pSharedPrefString,
                Constants.SharedPreferences.sDEFAULT_FLOAT_VALUE
            )
            Enums.SharePreferencesEnum.LongType.name -> mSharedPreferences.getLong(
                pSharedPrefString,
                Constants.SharedPreferences.sDEFAULT_LONG_VALUE.toLong()
            )
            else -> data
        }
    }

    fun setString(pKey: String, pValue: String) {
        setData(Enums.SharePreferencesEnum.StringType.name, pKey, pValue)
    }

    fun setInt(pKey: String, pValue: Int) {
        setData(Enums.SharePreferencesEnum.IntType.name, pKey, pValue)
    }

    fun setBoolean(pKey: String, pValue: Boolean) {
        setData(Enums.SharePreferencesEnum.BooleanType.name, pKey, pValue)
    }

    fun setFloat(pKey: String, pValue: Float) {
        setData(Enums.SharePreferencesEnum.FloatType.name, pKey, pValue)
    }

    fun setLong(pKey: String, pValue: Long) {
        setData(Enums.SharePreferencesEnum.LongType.name, pKey, pValue)
    }

    fun getString(pKey: String): String {
        return getData(Enums.SharePreferencesEnum.StringType.name, pKey) as? String ?: ""
    }

    fun getBoolean(pKey: String): Boolean {
        return getData(Enums.SharePreferencesEnum.BooleanType.name, pKey) as? Boolean ?: false
    }

    fun getInt(pKey: String): Int {
        return getData(Enums.SharePreferencesEnum.IntType.name, pKey) as? Int ?: -1
    }

    fun getInt(pKey: String, pDefaultValue: Int): Int {
        return getData(Enums.SharePreferencesEnum.IntType.name, pKey, pDefaultValue) as? Int ?: -1
    }

    /**
     * Clear all data from sharedPreferences
     * */
    fun clearAllData() {
        mSharedPreferences.edit().clear().apply()
    }
}
