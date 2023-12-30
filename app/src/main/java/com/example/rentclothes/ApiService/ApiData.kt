package com.example.rentclothes.ApiService

data class ApiData<T>(
    val status: Status,
    val data: T?
)
enum class Status{
    PROCESSING, SUCCESS, ERROR
}