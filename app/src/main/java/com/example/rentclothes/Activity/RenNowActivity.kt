package com.example.rentclothes.Activity

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.rentclothes.R
import com.example.rentclothes.core.AppCore
import com.example.rentclothes.databinding.ActivityRentnowBinding
import com.example.rentclothes.model.OrderReguest
import com.example.rentclothes.viewModel.RentNowscreenViewModel
import com.squareup.picasso.Picasso

class RenNowActivity:AppCompatActivity() {
    private lateinit var binding: ActivityRentnowBinding;
    private val viewModel= RentNowscreenViewModel();
    private var address: String? = null;
    private  var selectedSize: String? = null;
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        changeStatusBarColor(R.color.background_color)
        super.onCreate(savedInstanceState)
        binding = ActivityRentnowBinding.inflate(layoutInflater)
        val id = intent.getStringExtra("id");
        binding.title.text = intent.getStringExtra("title")
        binding.dayForRent.text = intent.getStringExtra("day") + " Day"
        binding.description.text = intent.getStringExtra("description")
        val imageUrl = intent.getStringExtra("image")
        Picasso.get().load(imageUrl).into(binding.image)
        val size = intent.getStringExtra("size")
        val sizeArray = size?.split(",")?.toTypedArray() ?: arrayOfNulls<String>(0)

        // Declare the activity result launcher outside the click listener
        val someActivityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                // Handle the result from the target activity
                val data: Intent? = result.data
                address = data?.getStringExtra("address");
                binding.address.text = address

                // Do something with the result
            } else if (result.resultCode == Activity.RESULT_CANCELED) {
                // Handle when the user cancels the operation
            }
        }

        setContentView(binding.root)
        binding.SpinnerSize.adapter = ArrayAdapter(
            AppCore.get().applicationContext,
            android.R.layout.simple_list_item_checked,
            sizeArray
        )

        binding.SpinnerSize.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                // Get the selected size from the sizeArray
                selectedSize = sizeArray[position]
                // Do something with the selected size, for example, display a Toast message
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Handle the case when no item is selected if needed
            }
        }
        binding.icLocation1.setOnClickListener {
            val intent = Intent(this,AddressActivity::class.java)
            someActivityResultLauncher.launch(intent)
        }
        binding.paymentMethod.setOnClickListener {
            val intent = Intent(this,ActivityPayment::class.java)
            startActivity(intent)
        }
        binding.btSummit.setOnClickListener {
            viewModel.orderScreen(OrderReguest(id,address,selectedSize))
        }
    }
    private fun changeStatusBarColor(colorResId: Int) {
        // Check if the version is at least Lollipop (API level 21)
        val window: Window = window
        val color: Int = ContextCompat.getColor(baseContext, colorResId)

        // Set the status bar color
        window.statusBarColor = color
    }
}
