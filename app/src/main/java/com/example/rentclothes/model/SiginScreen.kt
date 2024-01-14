package com.example.rentclothes.model

import com.google.gson.annotations.SerializedName

data class SiginScreen(
    @SerializedName("user") var user  : User,
    @SerializedName("token") var token : String
)
data class User(
    @SerializedName("id")
    var id:Int,
    @SerializedName("name")
    var name: String,
    @SerializedName("email_verified_at")
    var emailVerifiedAt : String,
    @SerializedName("created_at")
    var createdAt: String,
    @SerializedName("updated_at")
    var updatedAt: String
)
