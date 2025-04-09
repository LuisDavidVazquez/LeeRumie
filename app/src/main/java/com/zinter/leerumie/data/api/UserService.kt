package com.zinter.leerumie.data.api

import com.zinter.leerumie.data.model.RegisterRequest
import com.zinter.leerumie.data.model.LoginResponse
import com.zinter.leerumie.data.model.UpdateUserRequest
import com.zinter.leerumie.data.model.User
import retrofit2.Response
import retrofit2.http.*

interface UserService {
    @POST("users/create")
    suspend fun register(@Body request: RegisterRequest): Response<LoginResponse>

    @POST("users/register")
    suspend fun register(@Body user: UpdateUserRequest): Response<User>

    @PUT("users/update")
    suspend fun updateUser(@Body user: UpdateUserRequest): Response<User>
} 