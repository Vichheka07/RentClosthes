package com.example.rentclothes.viewModel
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rentclothes.ApiService.ApiData
import com.example.rentclothes.ApiService.Status
import com.example.rentclothes.model.OrderUpdateRequest
import com.example.rentclothes.model.client.ApiClient
import com.example.rentclothes.model.postResponse
import retrofit2.Call
import retrofit2.Response

class RentProductViewModel:ViewModel() {
    private val _rentProductData = MutableLiveData<ApiData<postResponse>>();
    val rentProductData: LiveData<ApiData<postResponse>>
        get() =_rentProductData
    fun updateOrderScreen(status: OrderUpdateRequest){
        val task = ApiClient.get().apiService.updateOrderScreen(status)
        task.enqueue(object : retrofit2.Callback<postResponse> {
            override fun onResponse(
                call: Call<postResponse>,
                response: Response<postResponse>
            ) {
                if (response.isSuccessful) {
                    val apiData = ApiData(Status.SUCCESS, response.body())
                    _rentProductData.postValue(apiData)
                } else {
                    Log.e("API_ERROR", "API Call Failed: ${response.errorBody()?.string()}")
                    val apiData = ApiData<postResponse>(Status.ERROR, null)
                    _rentProductData.postValue(apiData)
                }
            }

            override fun onFailure(call: Call<postResponse>, t: Throwable) {
                val apiData = ApiData<postResponse>(Status.ERROR, null)
                _rentProductData.postValue(apiData)
            }

        })
    }
}