package com.example.rentclothes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rentclothes.databinding.SheetsButtonBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ModalBottomSheet: BottomSheetDialogFragment() {
    private lateinit var binding: SheetsButtonBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.sheets_button, container, false)

    companion object {
        const val TAG = "ModalBottomSheet"
    }
}