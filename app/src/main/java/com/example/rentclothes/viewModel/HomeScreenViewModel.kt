package com.example.rentclothes.viewModel

import ApiItem
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rentclothes.ApiService.ApiData
import com.example.rentclothes.ApiService.Status
import com.example.rentclothes.Service.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeScreenViewModel:ViewModel(){

    private val _homescreenData = MutableLiveData<ApiData<ApiItem>>()
    val homescreenData: LiveData<ApiData<ApiItem>>
        get() =_homescreenData
    fun loadHomeScreen() {
        val httpClient = Retrofit.Builder()
            .baseUrl("https://rentclothes.online/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = httpClient.create(ApiService::class.java)
        val task = apiService.loadHomeScreenList();
        task.enqueue(object : Callback<ApiItem>{
            override fun onResponse(
                call: Call<ApiItem>,
                response: Response<ApiItem>
            ) {
                if (response.isSuccessful) {
                    val apiData = ApiData(Status.SUCCESS, response.body())
                    println("test  Success")
                    _homescreenData.postValue(apiData)
                } else {
                    Log.e("API_ERROR", "API Call Failed: ${response.errorBody()?.string()}")
                    val apiData = ApiData<ApiItem>(Status.ERROR, null)
                    _homescreenData.postValue(apiData)
                }
            }

            override fun onFailure(call: Call<ApiItem>, t: Throwable) {
                val apiData = ApiData<ApiItem>(Status.ERROR, null)
                _homescreenData.postValue(apiData)
            }

        })

    }
}