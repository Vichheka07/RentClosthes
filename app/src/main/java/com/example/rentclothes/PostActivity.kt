package com.example.rentclothes

import ImageAdapter
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.drawable.GradientDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Window
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rentclothes.databinding.ActivityPostBinding

class PostActivity: AppCompatActivity() {
    private lateinit var binding:ActivityPostBinding
    private lateinit var imageAdapter: ImageAdapter
    private val selectedImagesList = mutableListOf<Uri>()
    private var buttonXClickCount = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostBinding.inflate(layoutInflater)
        setContentView(binding.root)
        changeStatusBarColor(R.color.background_color)
        binding.arrowBack.setOnClickListener{
            finish()
        }
        binding.btS.setOnClickListener{
            resetButtonColors(binding.btFree)
            buttonXClickCount++
            if (buttonXClickCount >=2){
                resetButtonColors(binding.btS)
                buttonXClickCount = 0
            }else{
                chengePostbutton(binding.btS)
                buttonXClickCount++
            }
        }
        binding.btM.setOnClickListener{
            resetButtonColors(binding.btFree)
            buttonXClickCount++
            if (buttonXClickCount >=2){
                resetButtonColors(binding.btM)
                buttonXClickCount = 0
            }else{
                chengePostbutton(binding.btM)
                buttonXClickCount++
            }
        }
        binding.btL.setOnClickListener{
            resetButtonColors(binding.btFree)
            buttonXClickCount++
            if (buttonXClickCount >=2){
                resetButtonColors(binding.btL)
                buttonXClickCount = 0
            }else{
                chengePostbutton(binding.btL)
                buttonXClickCount++
            }
        }
        binding.btFree.setOnClickListener{
            buttonXClickCount = 0
            chengePostbutton(binding.btFree)
            resetButtonColors(binding.btS,binding.btM,binding.btL)
        }

//        Count text describe
//        Count text describe 0
        binding.faDescribe.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                val length = binding.faDescribe.length()
                val decount = 70 - length
                binding.textLimitFa.text = decount.toString()
            }

            })
        //        Count text describe 1
        binding.faDescribe1.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                val length = binding.faDescribe1.length()
                val decount = 1000 - length
                binding.textLimitFa1.text = decount.toString()
            }

        })
        //button Select Image
        binding.btSelectImg.setOnClickListener{
            val pickImage = Intent(Intent.ACTION_GET_CONTENT)
            pickImage.type = "image/*"
            pickImage.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
            changeImages.launch(Intent.createChooser(pickImage, "Select Images"))
        }
        val recyclerView: RecyclerView = findViewById(R.id.selected_image)
        imageAdapter = ImageAdapter(this, selectedImagesList)
        recyclerView.adapter = imageAdapter
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)

    }

    //This outside funtion main
    @SuppressLint("NotifyDataSetChanged")
    private val changeImages = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){result ->
        run {
            if (result.resultCode == Activity.RESULT_OK) {
                val data = result.data
                val clipData = data?.clipData
                if (clipData != null) {
                    // Multiple images selected
                    for (i in 0 until clipData.itemCount) {
                        val imageUri = clipData.getItemAt(i).uri
                        // Handle each selected image URI as needed
//                        binding.selectedImage.setImageURI(imageUri)
                        selectedImagesList.add(imageUri)


                    }
                } else {
                    // Single image selected
                    val imageUri = data?.data
                    // Handle the selected image URI as needed
                    if (imageUri != null) {
                        selectedImagesList.add(imageUri)
                    }
                }
            }
            imageAdapter.notifyDataSetChanged()
        }
    }
    //    Button chenge color
    private fun chengePostbutton(button: TextView){
    val selectedColor = resources.getColor(R.color.selected_button_color)
    (button.background as GradientDrawable).setColor(selectedColor)
    }
    @SuppressLint("ResourceAsColor")
    private fun resetButtonColors(vararg button: TextView){
        // Reset the background color of all buttons to the default color
        for (buttons in button) {
            val defaultcolor = resources.getColor(R.color.default_button_color)
            (buttons.background as GradientDrawable).setColor(defaultcolor)
        }
    }

    private fun changeStatusBarColor(colorResId: Int) {
        // Check if the version is at least Lollipop (API level 21)
        val window: Window = window
        val color: Int = ContextCompat.getColor(baseContext, colorResId)

        // Set the status bar color
        window.statusBarColor = color
    }
    override fun onDestroy() {
        super.onDestroy()
        changeStatusBarColor(R.color.white)
            resetButtonColors(binding.btFree)
            resetButtonColors(binding.btS,binding.btM,binding.btL)
        }
    }