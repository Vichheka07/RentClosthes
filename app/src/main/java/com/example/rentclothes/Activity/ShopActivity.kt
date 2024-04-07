package com.example.rentclothes.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rentclothes.databinding.ActivityShopBinding

class ShopActivity: AppCompatActivity(){
    private lateinit var binding: ActivityShopBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShopBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}