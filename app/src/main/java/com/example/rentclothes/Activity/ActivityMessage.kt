package com.example.rentclothes.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rentclothes.databinding.ActivityMessageBinding
import com.example.rentclothes.databinding.ActivitySearchBinding

class ActivityMessage :AppCompatActivity(){
    private lateinit var binding: ActivityMessageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMessageBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}