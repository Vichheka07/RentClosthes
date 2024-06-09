package com.example.rentclothes.Activity

import ImageAdapter
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.database.Cursor
import android.graphics.drawable.GradientDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.view.Window
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rentclothes.R
import com.example.rentclothes.databinding.ActivityPostBinding
import com.example.rentclothes.viewModel.PostScreenViewModel
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File


class PostActivity: AppCompatActivity() {
    private lateinit var binding:ActivityPostBinding
    private lateinit var imageAdapter: ImageAdapter
    private val selectedImagesList = mutableListOf<Uri>()
    private var buttonXClickCount = 0
    private val viewModel = PostScreenViewModel();
    private val size = mutableListOf<String>("Free");
    private var selectedCondition: String? =null;
    private var selectedDelivery: String ="Delivery";
    private var selectCategory : String ="KHMER"
    private var imageBody: MutableList<MultipartBody.Part> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityPostBinding.inflate(layoutInflater)
        setContentView(binding.root)
        changeStatusBarColor(R.color.background_color)
        binding.arrowBack.setOnClickListener{
            finish()
        }
        viewModel.PostScreenData.observe(this){resource ->
            Toast.makeText(this, "Success", Toast.LENGTH_LONG).show()
            val data = resource?.message;
        }
        binding.btS.setOnClickListener{
            resetButtonColors(binding.btFree)
            size.remove("S")
            if (size.get(0) == "Free"){
                size.clear();
            }
            buttonXClickCount++
            if (buttonXClickCount >=2){
                size.remove("S")
                resetButtonColors(binding.btS)
                buttonXClickCount = 0
            }else{
                chengePostbutton(binding.btS)
                size.add("S")
                buttonXClickCount++
            }
        }
        binding.btM.setOnClickListener{
            resetButtonColors(binding.btFree)
            if (size.get(0) == "Free"){
                size.clear();
            }
            buttonXClickCount++
            if (buttonXClickCount >=2){
                size.remove("M")
                resetButtonColors(binding.btM)
                buttonXClickCount = 0
            }else{
                chengePostbutton(binding.btM)
                size.add("M")
                buttonXClickCount++
            }
        }
        binding.btL.setOnClickListener{
            resetButtonColors(binding.btFree)
            if (size.get(0) == "Free"){
                size.clear();
            }
            buttonXClickCount++
            if (buttonXClickCount >=2){
                size.remove("L");
                resetButtonColors(binding.btL)
                buttonXClickCount = 0
            }else{
                size.add("L");
                chengePostbutton(binding.btL)
                buttonXClickCount++
            }
        }
        binding.btFree.setOnClickListener{
            size.clear();
            size.add("Free");
            buttonXClickCount = 0
            chengePostbutton(binding.btFree)
            resetButtonColors(binding.btS,binding.btM,binding.btL)
        }

        // Declare the activity result launcher outside the click listener
        val someActivityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                // Handle the result from the target activity
                val data: Intent? = result.data
                selectCategory = data?.getStringExtra("name").toString()
                binding.category.setText(selectCategory);
                // Do something with the result
            } else if (result.resultCode == Activity.RESULT_CANCELED) {
                // Handle when the user cancels the operation
            }
        }

// Set the click listener for the button
        binding.arrowNext.setOnClickListener{
            val intent = Intent(this, ActivityPostCategories::class.java)
            someActivityResultLauncher.launch(intent)
        }

        binding.choosCondition.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { group, checkedId ->
            val selectedRadioButton = findViewById<RadioButton>(checkedId)
            if (selectedRadioButton != null) {
                selectedCondition = selectedRadioButton.text.toString()

                println("test $selectedCondition")

                // Now you have the selected text, you can send it to the server
                // You may want to use an AsyncTask, Thread, or any other mechanism to handle the network request
            }
        })
        binding.choosDelivery.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { group, checkedId ->
            val selectedRadioButton = findViewById<RadioButton>(checkedId)
            if (selectedRadioButton != null) {
                selectedDelivery = selectedRadioButton.text.toString()

                println("test $selectedDelivery")

                // Now you have the selected text, you can send it to the server
                // You may want to use an AsyncTask, Thread, or any other mechanism to handle the network request
            }
        })

        // Button summit post data
        binding.btSummit.setOnClickListener{
            val title = binding.faDescribe.text.toString().trim()
            val description = binding.faDescribe1.text.toString().trim()
            val price = binding.rentPrice.text.toString().trim();
            val original = binding.originalPrice.text.toString().trim()
            val day = binding.dayForRent.text.toString().trim();
            val category = selectCategory
            val condition = selectedCondition
            val size = size.joinToString(",")
            val delivery =selectedDelivery;

            val titleBody: RequestBody = RequestBody.create(MediaType.parse("text/plain"), title)
            val descriptionBody: RequestBody = RequestBody.create(MediaType.parse("text/plain"), description)
            val priceBody: RequestBody = RequestBody.create(MediaType.parse("text/plain"), price)
            val originalBody: RequestBody = RequestBody.create(MediaType.parse("text/plain"), original)
            val dayBody: RequestBody = RequestBody.create(MediaType.parse("text/plain"), day)
            val categoryBody: RequestBody = RequestBody.create(MediaType.parse("text/plain"), category)
            val conditionBody: RequestBody = RequestBody.create(MediaType.parse("text/plain"), condition)
            val sizeBody: RequestBody = RequestBody.create(MediaType.parse("text/plain"), size)
            val deliveryBody: RequestBody = RequestBody.create(MediaType.parse("text/plain"), delivery)
            if (condition != null) {
                viewModel.postScreen(imageBody,titleBody,descriptionBody,priceBody,originalBody,dayBody,categoryBody,conditionBody,sizeBody,deliveryBody)
            }
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
    private fun getRealPathFromURI(uri: Uri): String {
        val cursor: Cursor?
        val column_index: Int
        val projection = arrayOf(MediaStore.Images.Media.DATA)
        cursor = contentResolver.query(uri, projection, null, null, null)
        column_index = cursor?.getColumnIndexOrThrow(MediaStore.Images.Media.DATA) ?: 0
        cursor?.moveToFirst()
        val filePath = cursor?.getString(column_index) ?: ""
        cursor?.close()
        return filePath
    }


    //This outside funtion main
    @SuppressLint("NotifyDataSetChanged")
    private val changeImages = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data
            val clipData = data?.clipData

            if (clipData != null) {
                // Multiple images selected
                imageBody.clear() // Clear existing images
                for (i in 0 until clipData.itemCount) {
                    val imageUri = clipData.getItemAt(i).uri
                    val file = File(getRealPathFromURI(imageUri))
                    val requestFile =
                        RequestBody.create(MediaType.parse("multipart/form-data"), file)
                    val part = MultipartBody.Part.createFormData("images[]", file.name, requestFile)
                    imageBody.add(part)
                    selectedImagesList.add(imageUri)
                }
            } else {
                // Single image selected
                val imageUri = data?.data
                imageBody.clear() // Clear existing images

                if (imageUri != null) {
                    val file = File(getRealPathFromURI(imageUri))
                    val requestFile =
                        RequestBody.create(MediaType.parse("multipart/form-data"), file)
                    val part = MultipartBody.Part.createFormData("images[]", file.name, requestFile)
                    imageBody.add(part)
                    selectedImagesList.add(imageUri)
                }
            }
            imageAdapter.notifyDataSetChanged()
        }
    }

    //    Button chenge color
    private fun chengePostbutton(button: TextView){
        val selectedColor = resources.getColor(R.color.purple_700)
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
    // button show text categories
}