package com.example.rentclothes

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.rentclothes.databinding.ActivityPostcategoriesBinding

class ActivityPostCategories: AppCompatActivity() {
    private lateinit var binding: ActivityPostcategoriesBinding
    private val name = listOf<String>("KHMER","GRADUATION","WEDDING","WEEKEND","HOME")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostcategoriesBinding.inflate(layoutInflater);
        setContentView(binding.root);
        binding.arrowBack.setOnClickListener{
            finish();
        }
        binding.imKhmer.setOnClickListener {
            val resultIntent = Intent()
            resultIntent.putExtra("name", name[0])
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }


        binding.imGraduate.setOnClickListener{
            val resultIntent = Intent()
            resultIntent.putExtra("name", name[1])
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
        binding.imWedding.setOnClickListener{
            val resultIntent = Intent()
            resultIntent.putExtra("name", name[2])
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
        binding.imWeekend.setOnClickListener{
            val resultIntent = Intent()
            resultIntent.putExtra("name", name[3])
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
        binding.imHome.setOnClickListener{
            val resultIntent = Intent()
            resultIntent.putExtra("name", name[4])
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }


    }
}