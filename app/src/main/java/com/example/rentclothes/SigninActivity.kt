package com.example.rentclothes

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.rentclothes.ApiService.Status
import com.example.rentclothes.databinding.ActivityLoginBinding
import com.example.rentclothes.viewModel.SigninScreenViewModel

class SigninActivity: AppCompatActivity (){
    private val viewModel = SigninScreenViewModel()
    lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.SiginScreenData.observe(this){ resource ->
            if (resource.status == Status.SUCCESS) {
                Toast.makeText(this, "Success", Toast.LENGTH_LONG).show()
                val data = resource.data
                finish()
            } else if (resource.status == Status.ERROR) {
                Toast.makeText(this, "Incorrect!", Toast.LENGTH_LONG).show()
            }
        }


        binding.button.setOnClickListener {
            val emailEditText = binding.editTextTextEmailAddress
            val passwordEditText = binding.editTextTextPassword

            // Retrieving the text from the EditText views inside the click listener
            val email: String = emailEditText.text.toString().trim()
            val password: String = passwordEditText.text.toString().trim()


            // Print or log the values to see the data submitted
            println("Email: $email, Password: $password")

            // Call the ViewModel method with the retrieved values
            viewModel.SignIn(email, password)
        }
    }

}