package com.example.rentclothes

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.coding.materialcarousel.adapters.ImageAdapter
import com.example.rentclothes.Fragment.CardFragment
import com.example.rentclothes.databinding.ActivityProditailBinding
import com.example.rentclothes.model.ImageItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.UUID

class ProductDetailActivity:AppCompatActivity(){
    private lateinit var binding: ActivityProditailBinding
    private lateinit var pagechangeListener: ViewPager2.OnPageChangeCallback


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProditailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val title = intent.getStringExtra("title")
        val imageList = intent.getStringExtra("images")
        val type = object : TypeToken<ArrayList<ImageItem>>(){}.type
        val testData = Gson().fromJson<ArrayList<ImageItem>>(imageList, type)
        val day = intent.getStringExtra("day")
        val price = intent.getStringExtra("price")
        val size = intent.getStringExtra("size")
        val description = intent.getStringExtra("description")
        println("Hel ${imageList}")

        binding.description1.text = description
        binding.Size.text = "Size $size"
        binding.title.text =title
        binding.dayForRent.text ="$price$/$day Day"
        binding.dayForRent1.text ="$price$/$day Day"


        showproductdetail(testData)
        binding.imageCard.setOnClickListener{
            val intent = Intent(this,RenNowActivity::class.java)
            startActivity(intent)
        }

    }
    private fun showproductdetail(imageList: ArrayList<ImageItem>) {
        val imageAdapter = ImageAdapter()
            binding.imageRV.adapter = imageAdapter
        imageAdapter.submitList(imageList)
    }

}