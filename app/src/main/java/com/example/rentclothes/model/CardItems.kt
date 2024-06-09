package com.example.rentclothes.model

data class CardItems (
    val data: List<CardItem>
)
data class CardItem(
    val id: Int,
    val images: List<Image>,
    val title: String,
    val describe: String,
    val name: String,
    val price: String,
    val day: String,
    val size: String,
    val address: String?,
    val delivery: String,
    val orderdate: String,
    val orderstatus: String?
)

data class Image(
    val url: String
)