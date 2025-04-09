package com.zinter.leerumie.data.model

data class UpdateUserRequest(
    val name: String,
    val email: String,
    val username: String,
    val password: String
) 