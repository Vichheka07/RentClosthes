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
import retrofit2.Response
import javax.security.auth.callback.Callback

class CategoryScreenViewModel:ViewModel() {
    private val _categoryscreenData = MutableLiveData<ApiData<ApiItem>>();
    val categoryscreenData: LiveData<ApiData<ApiItem>>
        get() =_categoryscreenData

    fun loadCategoryScreen(category: String){
        val task = ApiClient.get(AppCore.get().applicationContext).apiService.loadCategoryScreen(category);
        task.enqueue(object : retrofit2.Callback<ApiItem> {
            override fun onResponse(
                call: Call<ApiItem>,
                response: Response<ApiItem>
            ) {
                if (response.isSuccessful) {
                    val apiData = ApiData(Status.SUCCESS, response.body())
                    _categoryscreenData.postValue(apiData)
                } else {
                    Log.e("API_ERROR", "API Call Failed: ${response.errorBody()?.string()}")
                    val apiData = ApiData<ApiItem>(Status.ERROR, null)
                    _categoryscreenData.postValue(apiData)
                }
            }

            override fun onFailure(call: Call<ApiItem>, t: Throwable) {
                val apiData = ApiData<ApiItem>(Status.ERROR, null)
                _categoryscreenData.postValue(apiData)
            }

        })
    }
}