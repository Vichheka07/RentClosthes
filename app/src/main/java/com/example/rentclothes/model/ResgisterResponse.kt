package com.example.rentclothes.model

import com.google.gson.annotations.SerializedName

data class ResgisterResponse(
    @SerializedName("user")
    var user  : User,
    @SerializedName("token" )
    var token : String
)
data class User(
    @SerializedName("name"       ) var name      : String? = null,
    @SerializedName("email"      ) var email     : String? = null,
    @SerializedName("updated_at" ) var updatedAt : String? = null,
    @SerializedName("created_at" ) var createdAt : String? = null,
    @SerializedName("id"         ) var id        : Int?    = null
)