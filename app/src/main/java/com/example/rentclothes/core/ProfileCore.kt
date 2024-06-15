package com.example.rentclothes.core

import android.content.Context
import android.content.SharedPreferences

object ProfileCore {
    private const val PREFS_NAME = "my_profile"
    private lateinit var preferences: SharedPreferences

    private const val KEY_MY_DATA = "my_data"
    // Initialization
    fun init(context: Context) {
        preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    // Property to get/set data
    var myData: String?
        get() = preferences.getString(KEY_MY_DATA, null)
        set(value) {
            preferences.edit().putString(KEY_MY_DATA, value).apply()
        }
}