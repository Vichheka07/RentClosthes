package com.example.rentclothes.Activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.rentclothes.ApiService.Status
import com.example.rentclothes.R
import com.example.rentclothes.databinding.ActivityRentproductBinding
import com.example.rentclothes.model.OrderUpdateRequest
import com.example.rentclothes.viewModel.RentProductViewModel
import com.squareup.picasso.Picasso

class RentProductActivity: AppCompatActivity() {
    private lateinit var binding: ActivityRentproductBinding
    private val viewModel = RentProductViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRentproductBinding.inflate(layoutInflater);
        setContentView(binding.root)
        changeStatusBarColor(R.color.background_color)
        // Observe LiveData from ViewModel
        viewModel.rentProductData.observe(this) { resource ->
            if (resource.status == Status.SUCCESS) {
                Toast.makeText(this, "Yes", Toast.LENGTH_LONG).show()
                // Make sure to finish the current activity if you want to prevent the user from coming back to the login screen using the back button
                println("Completed ${resource.data}")
            } else if (resource.status == Status.ERROR) {
                Toast.makeText(this, "Maybe Network Slow", Toast.LENGTH_LONG).show()
            }
        }
        binding.btAccept.setOnClickListener {
            val data = intent.getStringExtra("id")?.let { it1 ->
                OrderUpdateRequest(
                    id = it1.toLong(),
                    orderstatus = "processing"
                )
            }
            if (data != null) {
                viewModel.updateOrderScreen(data)
            }
        }
        binding.btReject.setOnClickListener {
            val data = intent.getStringExtra("id")?.let { it1 ->
                OrderUpdateRequest(
                    id = it1.toLong(),
                    orderstatus = "cancelled"
                )
            }
            if (data != null) {
                viewModel.updateOrderScreen(data)
            }
        }
        val title = intent.getStringExtra("title");
        val image = intent.getStringExtra("image")
        val description = intent.getStringExtra("description")
        val orderStatus= intent.getStringExtra("orderstatus")
        val name = intent.getStringExtra("name")
        val price = intent.getStringExtra("price")
        val day = intent.getStringExtra("day")
        val delivery = intent.getStringExtra("delivery")
        val size = intent.getStringExtra("size")
        val address = intent.getStringExtra("address")
        val orderdate = intent.getStringExtra("orderdate")
        if (intent.getStringExtra("type") == "seller") {
            binding.btReject.visibility = View.VISIBLE
            binding.btAccept.visibility = View.VISIBLE
        } else {
            binding.btReject.visibility = View.GONE
            binding.btAccept.visibility = View.GONE
            binding.address.visibility = View.GONE
            binding.textAddress.visibility = View.GONE
        }
        binding.title.text = title;
        binding.textDescription.text = description;
        binding.orderStatus.text = orderStatus;
        binding.textName.text = name;
        binding.idPrice.text = "$price$";
        binding.idDay.text= day;
        binding.idDelivery.text = delivery;
        binding.idSize.text = size;
        binding.orderDate.text = orderdate;
        binding.textAddress.text = address;
        Picasso.get().load(image).into(binding.imageCard)
    }

    private fun changeStatusBarColor(colorResId: Int) {
        // Check if the version is at least Lollipop (API level 21)
        val window: Window = window
        val color: Int = ContextCompat.getColor(baseContext, colorResId)

        // Set the status bar color
        window.statusBarColor = color
    }
}