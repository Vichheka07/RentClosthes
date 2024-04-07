package com.example.rentclothes.Activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.rentclothes.databinding.ActivitySettingBinding
import com.example.rentclothes.utility.AppEncryptedPreference

class SettingActivity: AppCompatActivity() {
    private lateinit var binding: ActivitySettingBinding;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.LogOut.setOnClickListener{
            AppEncryptedPreference.get(this).storeApiTokend(null);
            Toast.makeText(this, "Success", Toast.LENGTH_LONG).show()
        }
    }
}