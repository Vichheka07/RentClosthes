package com.example.rentclothes.viewModel
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rentclothes.ApiService.ApiData
import com.example.rentclothes.ApiService.Status
import com.example.rentclothes.model.CardItems
import com.example.rentclothes.model.client.ApiClient
import retrofit2.Call
import retrofit2.Response

class CardScreenViewModel:ViewModel() {
    private val _cardscreenData = MutableLiveData<ApiData<CardItems>>();
    val cardscreenData: LiveData<ApiData<CardItems>>
        get() =_cardscreenData

    fun loadCategoryScreen(screenType: String){
        val task = if (screenType == "customer") {
            ApiClient.get().apiService.loadCustomerScreen()
        } else {
            ApiClient.get().apiService.loadRenterScreen()
        }
        task.enqueue(object : retrofit2.Callback<CardItems> {
            override fun onResponse(
                call: Call<CardItems>,
                response: Response<CardItems>
            ) {
                if (response.isSuccessful) {
                    val apiData = ApiData(Status.SUCCESS, response.body())
                    _cardscreenData.postValue(apiData)
                } else {
                    Log.e("API_ERROR", "API Call Failed: ${response.errorBody()?.string()}")
                    val apiData = ApiData<CardItems>(Status.ERROR, null)
                    _cardscreenData.postValue(apiData)
                }
            }

            override fun onFailure(call: Call<CardItems>, t: Throwable) {
                val apiData = ApiData<CardItems>(Status.ERROR, null)
                _cardscreenData.postValue(apiData)
            }

        })
    }
}