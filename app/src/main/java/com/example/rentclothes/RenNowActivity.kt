package com.example.rentclothes

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.rentclothes.core.AppCore
import com.example.rentclothes.databinding.ActivityRentnowBinding
import com.squareup.picasso.Picasso

class RenNowActivity:AppCompatActivity() {
    private lateinit var binding: ActivityRentnowBinding;

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        changeStatusBarColor(R.color.background_color)
        super.onCreate(savedInstanceState)
        binding = ActivityRentnowBinding.inflate(layoutInflater)
        binding.title.text = intent.getStringExtra("title")
        binding.dayForRent.text = intent.getStringExtra("day") + " Day"
        binding.description.text = intent.getStringExtra("description")
        val imageUrl = intent.getStringExtra("image")
        Picasso.get().load(imageUrl).into(binding.image)
        val size = intent.getStringExtra("size")
        val sizeArray = size?.split(",")?.toTypedArray() ?: arrayOfNulls<String>(0)

        setContentView(binding.root)
        binding.SpinnerSize.adapter = ArrayAdapter(
            AppCore.get().applicationContext,
            android.R.layout.simple_list_item_checked,
            sizeArray
        )
    }
    private fun changeStatusBarColor(colorResId: Int) {
        // Check if the version is at least Lollipop (API level 21)
        val window: Window = window
        val color: Int = ContextCompat.getColor(baseContext, colorResId)

        // Set the status bar color
        window.statusBarColor = color
    }
}
