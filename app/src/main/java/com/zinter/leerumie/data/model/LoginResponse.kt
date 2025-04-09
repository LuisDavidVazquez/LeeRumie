package com.zinter.leerumie.data.model

data class LoginResponse(
    val token: String,
    val user: User
)

data class User(
    val _id: String,
    val email: String,
    val name: String
) 