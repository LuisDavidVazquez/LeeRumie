package com.zinter.leerumie.ui.auth

sealed class LoginState {
    object Idle : LoginState()
    object Loading : LoginState()
    data class Success(val response: com.zinter.leerumie.data.model.LoginResponse) : LoginState()
    data class Error(val message: String) : LoginState()
}

sealed class RegisterState {
    object Idle : RegisterState()
    object Loading : RegisterState()
    data class Success(val response: com.zinter.leerumie.data.model.LoginResponse) : RegisterState()
    data class Error(val message: String) : RegisterState()
} 