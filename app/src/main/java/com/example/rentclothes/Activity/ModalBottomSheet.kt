package com.example.rentclothes.Activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rentclothes.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ModalBottomSheet: BottomSheetDialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.sheets_button, container, false)

    companion object {
        const val TAG = "ModalBottomSheet"
    }
}