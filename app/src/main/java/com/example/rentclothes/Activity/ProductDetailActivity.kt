package com.example.rentclothes.Activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.coding.materialcarousel.adapters.ImageAdapter
import com.example.rentclothes.databinding.ActivityProditailBinding
import com.example.rentclothes.model.ImageItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ProductDetailActivity:AppCompatActivity(){
    private lateinit var binding: ActivityProditailBinding


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
        val id = intent.getStringExtra("post_id");
        val description = intent.getStringExtra("description")
        println("Hel ${imageList}")


        binding.description1.text = description
        binding.Size.text = "$size"
        binding.title.text =title
        binding.dayForRent.text ="$price$/$day Day"
        binding.dayForRent1.text ="$price$/$day Day"


        showproductdetail(testData)
        binding.imageCard.setOnClickListener{
            val intent = Intent(this, RenNowActivity::class.java)
            intent.putExtra("id",id);
            intent.putExtra("title", title)
            intent.putExtra("day", day)
            intent.putExtra("description", description)
            intent.putExtra("image", testData[0].url)
            intent.putExtra("price", price)
            intent.putExtra("size", size)
            startActivity(intent)
        }

    }
    private fun showproductdetail(imageList: ArrayList<ImageItem>) {
        val imageAdapter = ImageAdapter()
            binding.imageRV.adapter = imageAdapter
        imageAdapter.submitList(imageList)
    }

}