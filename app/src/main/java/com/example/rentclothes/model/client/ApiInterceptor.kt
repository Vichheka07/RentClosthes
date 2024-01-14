package com.example.rentclothes.model.client

import com.example.rentclothes.core.AppCore
import com.example.rentclothes.utility.AppEncryptedPreference
import com.example.rentclothes.utility.AppPreference
import okhttp3.Interceptor
import okhttp3.Response

class ApiInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var token = AppEncryptedPreference.get(AppCore.get().applicationContext).getApiTokend()
        var request = if (token != null){
            chain.request().newBuilder().addHeader("Authorization", "Bearer $token").build()
        }else{
            chain.request()
        }
        return chain.proceed(request)
    }

}