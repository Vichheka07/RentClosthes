package com.example.rentclothes.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rentclothes.databinding.ActivityLocationBinding

class AddressActivity:AppCompatActivity() {
    private lateinit var binding: ActivityLocationBinding;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivityLocationBinding.inflate(layoutInflater).root)
    }
}