package com.example.rentclothes.viewModel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rentclothes.core.AppCore
import com.example.rentclothes.model.OrderReguest
import com.example.rentclothes.model.client.ApiClient
import com.example.rentclothes.model.postResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RentNowscreenViewModel: ViewModel() {
    private val _RentNowScreenData = MutableLiveData<postResponse?>()
    val RentNowScreenData: MutableLiveData<postResponse?>
        get() = _RentNowScreenData

    fun orderScreen(orders: OrderReguest) {
        val task = ApiClient.get().apiService.loadOrderScreen(orders)
        task.enqueue(object : Callback<postResponse> {
            override fun onResponse(
                call: Call<postResponse>,
                response: Response<postResponse>
            ) {
                if (response.isSuccessful) {
                    val postResponse: postResponse? = response.body()
                    _RentNowScreenData.postValue(postResponse)
                    Toast.makeText(AppCore.get().applicationContext, "Success", Toast.LENGTH_SHORT).show()
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
}