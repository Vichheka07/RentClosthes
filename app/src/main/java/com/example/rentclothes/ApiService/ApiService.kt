package com.example.rentclothes.Service
import ApiItem
import retrofit2.http.GET
import retrofit2.Call

interface ApiService {
    @GET("api/posts")
    fun loadHomeScreenList(): Call<ApiItem>
}