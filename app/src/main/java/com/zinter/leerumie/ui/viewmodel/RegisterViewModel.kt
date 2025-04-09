package com.zinter.leerumie.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zinter.leerumie.data.api.RetrofitClient
import com.zinter.leerumie.data.model.RegisterRequest
import com.zinter.leerumie.ui.auth.RegisterState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {
    private val _registerState = MutableStateFlow<RegisterState>(RegisterState.Idle)
    val registerState: StateFlow<RegisterState> = _registerState

    fun register(name: String, username: String, email: String, password: String) {
        viewModelScope.launch {
            _registerState.value = RegisterState.Loading
            try {
                val response = RetrofitClient.userService.register(RegisterRequest(name, username, email, password))
                if (response.isSuccessful) {
                    _registerState.value = RegisterState.Success(response.body()!!)
                } else {
                    _registerState.value = RegisterState.Error("Error al registrarse")
                }
            } catch (e: Exception) {
                _registerState.value = RegisterState.Error(e.message ?: "Error desconocido")
            }
        }
    }
} 