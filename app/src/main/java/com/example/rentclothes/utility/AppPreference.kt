package com.example.rentclothes.utility

import android.content.Context
import android.content.SharedPreferences
import com.example.rentclothes.model.client.ApiClient

class AppPreference private constructor(context: Context){
    private lateinit var pref: SharedPreferences
    init {
        pref = context.getSharedPreferences("myapp", Context.MODE_PRIVATE)
    }
    companion object{
        private var instance: AppPreference?=null

        fun get(context: Context): AppPreference{
            if (instance == null){
                instance = AppPreference(context)
            }
            return instance!!
        }
    }
}