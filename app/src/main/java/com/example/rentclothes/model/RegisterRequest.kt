package com.example.rentclothes.model

data class RegisterRequest(
    private val name: String,
    private val email: String,
    private val password: String,
    private val password_confirmation: String
)