package com.example.rentclothes.model

import com.google.gson.annotations.SerializedName

data class postResponse(
    @SerializedName("message") var message : String? = null
)