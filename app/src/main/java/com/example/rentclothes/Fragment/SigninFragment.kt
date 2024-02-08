package com.example.rentclothes.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.rentclothes.ApiService.Status
import com.example.rentclothes.databinding.SigninFragmentBinding
import com.example.rentclothes.viewModel.SigninScreenViewModel

class SigninFragment: Fragment() {
    private lateinit var binding: SigninFragmentBinding
    private val viewModel = SigninScreenViewModel()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SigninFragmentBinding.inflate(layoutInflater)
        return (binding.root)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.SiginScreenData.observe(viewLifecycleOwner){ resource ->
            if (resource.status == Status.SUCCESS) {
                Toast.makeText(context, "Success", Toast.LENGTH_LONG).show()
                // Make sure to finish the current activity if you want to prevent the user from coming back to the login screen using the back button
                activity?.finish()
            } else if (resource.status == Status.ERROR) {
                Toast.makeText(context, "Incorrect!", Toast.LENGTH_LONG).show()
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