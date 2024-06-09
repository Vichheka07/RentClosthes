package com.example.rentclothes.Activity

import HomeFragment
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.myapplication.Fragment.FavoritFragment
import com.example.rentclothes.Fragment.CardFragment
import com.example.rentclothes.Fragment.ProfileFragment
import com.example.rentclothes.R
import com.example.rentclothes.core.AppCore
import com.example.rentclothes.core.LanguageCore
import com.example.rentclothes.databinding.ActivityMainBinding
import com.example.rentclothes.utility.AppEncryptedPreference
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        LanguageCore.init(this)
        setLocale(LanguageCore.myData)
        loadFragment(HomeFragment())
        binding.icPost.setOnClickListener{
            if(AppEncryptedPreference.get(AppCore.get().applicationContext).getApiTokend()!= null){
                val intent = Intent(this, PostActivity::class.java)
                startActivity(intent)
            }else{
                val intent = Intent(this, SigninActivity::class.java)
                startActivity(intent)
            }

        }
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    loadFragment(HomeFragment())
                }
                R.id.Favorite -> {
                    if(AppEncryptedPreference.get(this).getApiTokend()!=null){
                        loadFragment(FavoritFragment())
                    }else{
                        val intent = Intent(this, SigninActivity::class.java)
                        startActivity(intent)
                    }
                }
                R.id.Card -> {
                    if(AppEncryptedPreference.get(this).getApiTokend()!=null){
                        loadFragment(CardFragment())
                    }else{
                        val intent = Intent(this, SigninActivity::class.java)
                        startActivity(intent)
                    }
                }

                R.id.Profile -> {
                if(AppEncryptedPreference.get(this).getApiTokend()!=null){
                    loadFragment(ProfileFragment())
                }else{
                    val intent = Intent(this, SigninActivity::class.java)
                    startActivity(intent)
                }
                }
                else -> {
                }
            }
            true
        }
    }
    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.lyFragment, fragment)
        transaction.commit()
    }
    private fun setLocale(languageCode: String?) {
        val locale = languageCode?.let { Locale(it) }
        if (locale != null) {
            Locale.setDefault(locale)
        }
        val configuration = Configuration()
        configuration.setLocale(locale)
        resources.updateConfiguration(configuration, resources.displayMetrics)
        val sharedPreferences = getSharedPreferences("Settings", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("Language", languageCode)
        editor.apply()
    }
}
