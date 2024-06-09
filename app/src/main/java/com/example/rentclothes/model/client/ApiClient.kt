package com.example.rentclothes.model.client
import com.example.rentclothes.Service.ApiService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient private constructor() {
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(ApiInterceptor())
        .build()



    private val httpClient = Retrofit.Builder()
        .baseUrl("http://192.168.27.36:8000/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    val apiService = httpClient.create(ApiService::class.java)
    companion object {
        private const val KEY_TOKEN = "token"
       private  var instance: ApiClient? = null
        fun get(): ApiClient{
            if (instance == null){
                instance = ApiClient()
            }
            return instance!!
        }
    }
}