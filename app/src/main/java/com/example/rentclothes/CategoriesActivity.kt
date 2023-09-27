package com.example.rentclothes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rentclothes.databinding.ActivityCategoriesBinding

class CategoriesActivity: AppCompatActivity() {
    private lateinit var binding: ActivityCategoriesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoriesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.arrowBack.setOnClickListener{
            finish()
        }
        binding.icFilter.setOnClickListener{
            val modalBottomSheet = ModalBottomSheet()
            modalBottomSheet.show(supportFragmentManager, ModalBottomSheet.TAG)
        }
    }
}