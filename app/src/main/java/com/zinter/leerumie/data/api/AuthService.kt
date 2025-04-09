package com.zinter.leerumie.data.api

import com.zinter.leerumie.data.model.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("auth/login")
    suspend fun login(@Body credentials: LoginCredentials): Response<LoginResponse>
}

data class LoginCredentials(
    val email: String,
    val password: String
) 