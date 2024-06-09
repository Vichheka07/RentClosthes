package com.example.rentclothes.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rentclothes.ApiService.ApiData
import com.example.rentclothes.ApiService.Status
import com.example.rentclothes.core.AppCore
import com.example.rentclothes.model.RegisterRequest
import com.example.rentclothes.model.ResgisterResponse
import com.example.rentclothes.model.client.ApiClient
import com.example.rentclothes.utility.AppEncryptedPreference
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignupScreenViewModel:ViewModel() {
    private val _SignupScreenData = MutableLiveData<ApiData<ResgisterResponse>>()
    val SignupScreenData: LiveData<ApiData<ResgisterResponse>>
        get() =_SignupScreenData

    fun SignUp(name: String,email: String,password:String,password_confirmation:String){
        val registerRequest = RegisterRequest(name = name, email = email, password = password, password_confirmation = password_confirmation)
        var task = ApiClient.get().apiService.loadSignUpScreen(registerRequest);
        task.enqueue(object : Callback<ResgisterResponse> {
            override fun onResponse(
                call: Call<ResgisterResponse>,
                response: Response<ResgisterResponse>
            ) {
                if (response.isSuccessful) {
                    val apiData = ApiData(Status.SUCCESS, response.body())
                    println("test  Success ${response.body()}")
                    var token = apiData.data?.token
                    if (token != null) {
                        AppEncryptedPreference.get(AppCore.get().applicationContext).storeApiTokend(token)
                    }
                    _SignupScreenData.postValue(apiData)
                } else {
                    Log.e("API_ERROR", "API Call Failed: ${response.errorBody()?.string()}")
                    val apiData = ApiData<ResgisterResponse>(Status.ERROR, null)
                    _SignupScreenData.postValue(apiData)
                }
            }

            override fun onFailure(call: Call<ResgisterResponse>, t: Throwable) {
                Log.e("API_ERROR", "API Call Failed", t)
                val apiData = ApiData<ResgisterResponse>(Status.ERROR, null)
                _SignupScreenData.postValue(apiData)
            }


        })
    }
}