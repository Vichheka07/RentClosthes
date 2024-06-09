package com.example.rentclothes.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rentclothes.model.client.ApiClient
import com.example.rentclothes.model.postResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostScreenViewModel : ViewModel() {
    private val _PostScreenData = MutableLiveData<postResponse?>()
    val PostScreenData: MutableLiveData<postResponse?>
        get() = _PostScreenData

    fun postScreen(
        images: List<MultipartBody.Part>, title: RequestBody, describe: RequestBody, price: RequestBody,
        orgprice: RequestBody, day: RequestBody, category: RequestBody, condition: RequestBody,
        size: RequestBody, delivery: RequestBody
    ) {
        val task = ApiClient.get().apiService.loadPostScreen(images,
            title,describe,price,orgprice,day,category,condition,size,delivery)
        task.enqueue(object : Callback<postResponse> {
            override fun onResponse(
                call: Call<postResponse>,
                response: Response<postResponse>
            ) {
                if (response.isSuccessful) {
                    val postResponse: postResponse? = response.body()
                    _PostScreenData.postValue(postResponse)
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
}

