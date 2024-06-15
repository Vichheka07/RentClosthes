package com.example.rentclothes.Fragment

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.rentclothes.Activity.LanguageActivity
import com.example.rentclothes.Activity.SettingActivity
import com.example.rentclothes.Activity.ShopActivity
import com.example.rentclothes.ApiService.Status
import com.example.rentclothes.core.ProfileCore
import com.example.rentclothes.databinding.ProfileFragmentBinding
import com.example.rentclothes.viewModel.ProfleViewModel
import com.example.rentclothes.viewModel.SigninScreenViewModel
import com.squareup.picasso.Picasso
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class ProfileFragment : Fragment() {
    private lateinit var binding: ProfileFragmentBinding
    private lateinit var userViewModel: SigninScreenViewModel
    private lateinit var viewModel: ProfleViewModel
    private var imageBody: MutableList<MultipartBody.Part> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ProfileFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userViewModel = ViewModelProvider(this).get(SigninScreenViewModel::class.java)
        viewModel = ViewModelProvider(this).get(ProfleViewModel::class.java)
        ProfileCore.init(requireContext());
        Picasso.get().load(ProfileCore.myData).into(binding.imUser)
        viewModel.loadProfile();
        viewModel.loadScreenData.observe(requireActivity()) { resource ->
            if (resource.status == Status.SUCCESS) {
                Toast.makeText(requireContext(), "Yes", Toast.LENGTH_LONG).show()
                Picasso.get().load(resource.data?.url).into(binding.imUser)
                binding.textName.text = resource.data?.name
            } else if (resource.status == Status.ERROR) {
                Toast.makeText(requireContext(), "Maybe Network Slow", Toast.LENGTH_LONG).show()
            }
        }
        binding.setting.setOnClickListener {
            val intent = Intent(requireActivity(), SettingActivity::class.java)
            startActivity(intent)
        }
        binding.shopActivity.setOnClickListener {
            val intent = Intent(requireActivity(), ShopActivity::class.java)
            startActivity(intent)
        }
        binding.language.setOnClickListener {
            val intent = Intent(requireActivity(), LanguageActivity::class.java)
            startActivity(intent)
        }
        binding.imProfile.setOnClickListener {
            val pickImage = Intent(Intent.ACTION_GET_CONTENT)
            pickImage.type = "image/*"
            changeImages.launch(Intent.createChooser(pickImage, "Select Image"))
        }

        viewModel.PostScreenData.observe(requireActivity()) { resource ->
            if (resource.status == Status.SUCCESS) {
                Toast.makeText(requireContext(), "Yes", Toast.LENGTH_LONG).show()
                println("Completed ${resource.data}")
            } else if (resource.status == Status.ERROR) {
                Toast.makeText(requireContext(), "Maybe Network Slow", Toast.LENGTH_LONG).show()
            }
        }

        userViewModel.SiginScreenData.observe(viewLifecycleOwner) { resource ->
            if (resource.status == Status.SUCCESS) {
                Toast.makeText(context, "Upload Success", Toast.LENGTH_LONG).show()
                println("kks ${resource.data}")
            } else if (resource.status == Status.ERROR) {
                Toast.makeText(context, "Incorrect!", Toast.LENGTH_LONG).show()
            }
        }
    }

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
                    val part = MultipartBody.Part.createFormData("image", file.name, requestFile)
                    imageBody.add(part)
                    viewModel.postProfile(imageBody)
                    Picasso.get().load(imageUri).into(binding.imUser)
                    ProfileCore.myData = imageUri.toString()
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
                    viewModel.postProfile(imageBody)
                    ProfileCore.myData = imageUri.toString()
                }
            }
        }
    }


    private fun getRealPathFromURI(uri: Uri): String {
        var path = ""
        val cursor = context?.contentResolver?.query(uri, null, null, null, null)
        cursor?.let {
            if (it.moveToFirst()) {
                val idx = it.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
                if (idx != -1) {
                    path = it.getString(idx)
                }
            }
            it.close()
        }
        return path
    }
}
