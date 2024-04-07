package com.example.rentclothes.viewModel

import ApiItem
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rentclothes.ApiService.ApiData
import com.example.rentclothes.ApiService.Status
import com.example.rentclothes.core.AppCore
import com.example.rentclothes.model.SearchRequestBody
import com.example.rentclothes.model.client.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchScreenViewModel: ViewModel() {
    private val _searchscreenData = MutableLiveData<ApiData<ApiItem>>();
    val searchscreenData: LiveData<ApiData<ApiItem>>
        get() =_searchscreenData

    fun loadSearchScreen(title: String){
        var task = ApiClient.get(AppCore.get().applicationContext).apiService.loadSearchScreen(
            SearchRequestBody(title)
        );
        task.enqueue(object : Callback<ApiItem>{
            override fun onResponse(
                call: Call<ApiItem>,
                response: Response<ApiItem>
            ) {
                if (response.isSuccessful) {
                    val apiData = ApiData(Status.SUCCESS, response.body())
                    _searchscreenData.postValue(apiData)
                } else {
                    Log.e("API_ERROR", "API Call Failed: ${response.errorBody()?.string()}")
                    val apiData = ApiData<ApiItem>(Status.ERROR, null)
                    _searchscreenData.postValue(apiData)
                }
            }

            override fun onFailure(call: Call<ApiItem>, t: Throwable) {
                val apiData = ApiData<ApiItem>(Status.ERROR, null)
                _searchscreenData.postValue(apiData)
            }

        })
    }
}