package com.example.rentclothes.model

import com.google.gson.annotations.SerializedName
import okhttp3.MultipartBody
import okhttp3.RequestBody

data class PostRequest(
    @SerializedName("images[]")
    private var images: List<MultipartBody.Part>,
    @SerializedName("title")// List to handle multiple images
    private var title: RequestBody,
    @SerializedName("describe")
    private var describe: RequestBody,
    @SerializedName("price")
    private var price: RequestBody,
    @SerializedName("orgprice")
    private var orgprice: RequestBody,
    @SerializedName("day")
    private var day: RequestBody,
    @SerializedName("category")
    private var category: RequestBody?,
    @SerializedName("codition")
    private var condition: RequestBody?,
    @SerializedName("size")
    private var size: RequestBody?,
    @SerializedName("delivery")
    private var delivery: RequestBody?
)
