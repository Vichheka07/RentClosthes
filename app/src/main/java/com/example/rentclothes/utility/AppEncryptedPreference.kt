package com.example.rentclothes.utility

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

class AppEncryptedPreference private constructor(context: Context){
    private lateinit var pref: SharedPreferences

    init {
        val masterKey = MasterKey.Builder(context).setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build()
        pref = EncryptedSharedPreferences.create(
            context,
            "myadppenc",
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )

    }
    fun storeApiTokend(token: String?){
        pref.edit().putString(KEY_TOCKEN, token).apply()
    }
    fun getApiTokend(): String?{
        return pref.getString(KEY_TOCKEN, null)
    }
    companion object{
        private var instance: AppEncryptedPreference?=null
        private const val KEY_TOCKEN = "token"

        fun get(context: Context): AppEncryptedPreference{
            if (instance == null){
                instance = AppEncryptedPreference(context)
            }
            return instance!!
        }
    }

}