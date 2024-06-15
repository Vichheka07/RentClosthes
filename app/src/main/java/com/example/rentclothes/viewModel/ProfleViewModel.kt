package com.example.rentclothes.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rentclothes.ApiService.ApiData
import com.example.rentclothes.ApiService.Status
import com.example.rentclothes.model.ProfileResponse
import com.example.rentclothes.model.client.ApiClient
import com.example.rentclothes.model.postResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfleViewModel: ViewModel(){
    private val _PostScreenData = MutableLiveData<ApiData<postResponse>>()
    val PostScreenData: LiveData<ApiData<postResponse>>
        get() = _PostScreenData

    private val _loadScreenData = MutableLiveData<ApiData<ProfileResponse>>()
    val loadScreenData: LiveData<ApiData<ProfileResponse>>
        get() = _loadScreenData

    fun postProfile(
        images: List<MultipartBody.Part>
    ) {
        val task = ApiClient.get().apiService.uploadProfileScreen(images)
        task.enqueue(object : Callback<postResponse> {
            override fun onResponse(
                call: Call<postResponse>,
                response: Response<postResponse>
            ) {
                if (response.isSuccessful) {
                    val apiData = ApiData(Status.SUCCESS, response.body())
                    _PostScreenData.postValue(apiData)
                    Log.d("test", "API IS Success")
                } else {
                    Log.d("test", "Response Body: ${response.body().toString()}")
                    // Handle error response
                    // Example: notify the UI with an error message
                }
            }

            override fun onFailure(call: Call<postResponse>, t: Throwable) {
                Log.e("test", "API Call Failed: ${t.message}")
                // Handle failure here, e.g., notify the UI with an error message
            }

        })
    }
    fun loadProfile() {
        val task = ApiClient.get().apiService.loadProfileScreen()
        task.enqueue(object : Callback<ProfileResponse> {
            override fun onResponse(
                call: Call<ProfileResponse>,
                response: Response<ProfileResponse>
            ) {
                if (response.isSuccessful) {
                    val apiData = ApiData(Status.SUCCESS, response.body())
                    _loadScreenData.postValue(apiData)
                    Log.d("test", "API IS Success")
                } else {
                    Log.d("test", "Response Body: ${response.body().toString()}")
                    // Handle error response
                    // Example: notify the UI with an error message
                }
            }

            override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {
                Log.e("test", "API Call Failed: ${t.message}")
                // Handle failure here, e.g., notify the UI with an error message
            }

        })
    }
}