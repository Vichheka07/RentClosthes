package com.example.rentclothes.viewModel

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rentclothes.ApiService.ApiData
import com.example.rentclothes.ApiService.Status
import com.example.rentclothes.Fragment.ProfileFragment
import com.example.rentclothes.core.AppCore
import com.example.rentclothes.model.LoginRequest
import com.example.rentclothes.model.SiginScreen
import com.example.rentclothes.model.client.ApiClient
import com.example.rentclothes.utility.AppEncryptedPreference
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SigninScreenViewModel:ViewModel() {
    private val _SiginScreenData = MutableLiveData<ApiData<SiginScreen>>()
    val SiginScreenData: LiveData<ApiData<SiginScreen>>
        get() =_SiginScreenData
    init {
        SiginScreenData
    }
    fun SignIn(email: String,password:String){
        val requestLogin = LoginRequest(
            email = email,
            password = password,
        )
        var task = ApiClient.get(AppCore.get().applicationContext).apiService.loadSignInScreen(requestLogin);
        task.enqueue(object : Callback<SiginScreen> {
            override fun onResponse(
                call: Call<SiginScreen>,
                response: Response<SiginScreen>
            ) {
                if (response.isSuccessful) {
                    val apiData = ApiData(Status.SUCCESS, response.body())
                    println("test  Success ${response.body()}")
                    var token = apiData.data?.token
                    var name = apiData.data?.user?.name
                    val bundle = Bundle()
                    bundle.putString("name", apiData.data?.user?.name)
                    val receiverFragment = ProfileFragment()
                    receiverFragment.arguments = bundle
                    if (token != null) {
                        AppEncryptedPreference.get(AppCore.get().applicationContext).storeApiTokend(token)
                    }
                    _SiginScreenData.postValue(apiData)
                } else {
                    Log.e("API_ERROR", "API Call Failed: ${response.errorBody()?.string()}")
                    val apiData = ApiData<SiginScreen>(Status.ERROR, null)
                    _SiginScreenData.postValue(apiData)
                }
            }

            override fun onFailure(call: Call<SiginScreen>, t: Throwable) {
                val apiData = ApiData<SiginScreen>(Status.ERROR, null)
                _SiginScreenData.postValue(apiData)
            }

        })
    }
}