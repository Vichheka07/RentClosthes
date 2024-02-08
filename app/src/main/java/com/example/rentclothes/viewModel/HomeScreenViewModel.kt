package com.example.rentclothes.viewModel

import ApiItem
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rentclothes.ApiService.ApiData
import com.example.rentclothes.ApiService.Status
import com.example.rentclothes.core.AppCore
import com.example.rentclothes.model.client.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeScreenViewModel:ViewModel(){

    private val _homescreenData = MutableLiveData<ApiData<ApiItem>>()
    val homescreenData: LiveData<ApiData<ApiItem>>
        get() =_homescreenData


    fun loadHomeScreen() {
        val task = ApiClient.get(AppCore.get().applicationContext).apiService.loadHomeScreenList();
        task.enqueue(object : Callback<ApiItem>{
            override fun onResponse(
                call: Call<ApiItem>,
                response: Response<ApiItem>
            ) {
                if (response.isSuccessful) {
                    val apiData = ApiData(Status.SUCCESS, response.body())
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