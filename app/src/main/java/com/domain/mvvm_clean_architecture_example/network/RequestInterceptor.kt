package com.domain.mvvm_clean_architecture_example.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.domain.mvvm_clean_architecture_example.R
import com.domain.mvvm_clean_architecture_example.constant.Constants
import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber
import java.io.IOException

class RequestInterceptor(context: Context?, private val authorizationToken: String?) : Interceptor {

    private val applicationContext = context?.applicationContext
    private val sBearer = "Bearer"
    private val sAuthorizationHeader = "Authorization"
    private val sContentType = "Content-type"
    private val sContentTypeJson = "application/json"

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isInternetAvailable(applicationContext))
            throw NoInternetException(
                applicationContext?.getString(R.string.check_internet_connection)
            )
        try {
            val originalRequest = chain.request()
            val requestBuilder = originalRequest.newBuilder()
                .apply {
                    if (authorizationToken != null && authorizationToken.isNotEmpty()) {
                        header(
                            sAuthorizationHeader,
                            sBearer + Constants.sSpaceString + authorizationToken
                        )
                    }
                }
                .addHeader(sContentType, sContentTypeJson)
                .method(originalRequest.method, originalRequest.body)
            val request = requestBuilder.url(originalRequest.url).build()
            Timber.d(request.toString())
            return chain.proceed(request)
        } catch (exception: Exception) {
            throw NoServerFoundException(exception.message)
        }
    }

    companion object {
        fun isInternetAvailable(applicationContext: Context?): Boolean {
            var result = false
            val connectivityManager =
                applicationContext?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
            connectivityManager?.let {
                it.getNetworkCapabilities(connectivityManager.activeNetwork)?.apply {
                    result = when {
                        hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                        else -> false
                    }
                }
            }
            return result
        }
    }

    class NoInternetException(message: String?) : IOException(message)
    class NoServerFoundException(message: String?) : IOException(message)
}