package com.zinter.leerumie.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zinter.leerumie.data.api.RetrofitClient
import com.zinter.leerumie.data.model.UpdateUserRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {
    private val _profileState = MutableStateFlow<ProfileState>(ProfileState.Idle)
    val profileState: StateFlow<ProfileState> = _profileState

    fun updateProfile(name: String, email: String, password: String) {
        viewModelScope.launch {
            _profileState.value = ProfileState.Loading
            try {
                val updateRequest = UpdateUserRequest(
                    name = name,
                    email = email,
                    username = "", // Este campo es requerido por la API pero no lo estamos usando en la UI
                    password = password
                )
                val response = RetrofitClient.userService.updateUser(updateRequest)
                if (response.isSuccessful) {
                    _profileState.value = ProfileState.Success("Perfil actualizado exitosamente")
                } else {
                    _profileState.value = ProfileState.Error("Error al actualizar el perfil")
                }
            } catch (e: Exception) {
                _profileState.value = ProfileState.Error(e.message ?: "Error desconocido")
            }
        }
    }

    fun logout() {
        // Aquí podrías limpiar el token de autenticación y otros datos de sesión
        _profileState.value = ProfileState.Logout
    }
}

sealed class ProfileState {
    object Idle : ProfileState()
    object Loading : ProfileState()
    data class Success(val message: String) : ProfileState()
    data class Error(val message: String) : ProfileState()
    object Logout : ProfileState()
} 