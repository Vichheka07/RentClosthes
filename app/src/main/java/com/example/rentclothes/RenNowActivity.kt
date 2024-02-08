package com.example.rentclothes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rentclothes.databinding.ActivityRentnowBinding

class RenNowActivity:AppCompatActivity() {
    private lateinit var binding: ActivityRentnowBinding;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRentnowBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}