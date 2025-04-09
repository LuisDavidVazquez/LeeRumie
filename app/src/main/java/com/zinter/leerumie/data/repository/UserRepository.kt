package com.zinter.leerumie.data.repository

import com.zinter.leerumie.data.api.AuthService
import com.zinter.leerumie.data.api.LoginCredentials
import com.zinter.leerumie.data.api.UserService
import com.zinter.leerumie.data.model.LoginResponse
import com.zinter.leerumie.data.model.RegisterRequest
import com.zinter.leerumie.data.model.UpdateUserRequest
import com.zinter.leerumie.data.model.User
import retrofit2.Response

class UserRepository(
    private val userService: UserService,
    private val authService: AuthService
) {
    suspend fun login(request: LoginCredentials): Response<LoginResponse> {
        return authService.login(request)
    }

    suspend fun register(request: RegisterRequest): Response<LoginResponse> {
        return userService.register(request)
    }

    suspend fun updateUser(user: UpdateUserRequest): Response<User> {
        return userService.updateUser(user)
    }
} 