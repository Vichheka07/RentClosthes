package com.example.rentclothes

import HomeFragment
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.myapplication.Fragment.FavoritFragment
import com.example.rentclothes.Fragment.CardFragment
import com.example.rentclothes.Fragment.ProfileFragment
import com.example.rentclothes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadFragment(HomeFragment())
        binding.icPost.setOnClickListener{
            val intent = Intent(this, PostActivity::class.java)
            startActivity(intent)
        }
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    loadFragment(HomeFragment())
                }
                R.id.Favorite -> {
                    loadFragment(FavoritFragment())
                }
                R.id.Card -> {
                    loadFragment(CardFragment())
                }R.id.Profile -> {
                    loadFragment(ProfileFragment())
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
}
