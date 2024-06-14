package com.example.rentclothes.model

import com.google.gson.annotations.SerializedName

data class OrderUpdateRequest(
    private var orderstatus: String,
    @SerializedName("id")
    private var id: Long
)