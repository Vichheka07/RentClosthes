package com.example.rentclothes.Activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rentclothes.databinding.ActivityLocationBinding

class AddressActivity:AppCompatActivity() {
    private lateinit var binding: ActivityLocationBinding;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLocationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ButtonAdd.setOnClickListener {
            val name = binding.editTextName.text.toString();
            val address =binding.editTextAddress.text.toString();
            val phone = binding.editTextPhone.text.toString();
            val addressdetail = binding.EditTextAddressDetail.text.toString();
            val resultIntent = Intent()
            resultIntent.putExtra("address", "Name:"+name+" Address:"+address+" Phone:"+phone+" AddressDetail:"+addressdetail)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
}