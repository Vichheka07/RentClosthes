package com.example.rentclothes.model.client

import android.content.Context
import android.content.SharedPreferences
import com.example.rentclothes.Service.ApiService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient private constructor(context: Context){
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(ApiInterceptor())
        .build()



    private val httpClient = Retrofit.Builder()
        .baseUrl("https://rentclothes.online/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    val apiService = httpClient.create(ApiService::class.java)
    companion object {
        private const val KEY_TOKEN = "token"
       private  var instance: ApiClient? = null
        fun get(context: Context): ApiClient{
            if (instance == null){
                instance = ApiClient(context)
            }
            return instance!!
        }
    }
}