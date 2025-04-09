package com.zinter.leerumie

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.zinter.leerumie.ui.screen.LoginScreen
import com.zinter.leerumie.ui.screen.RegisterScreen
import com.zinter.leerumie.ui.screen.ProfileScreen
import com.zinter.leerumie.ui.components.BottomNavigationBar
import com.zinter.leerumie.ui.components.BottomNavItem
import com.zinter.leerumie.ui.theme.LeeRumieTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LeeRumieTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    AppNavHost(navController = navController)
                }
            }
        }
    }
}

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true }
                    }
                },
                onNavigateToRegister = {
                    navController.navigate("register")
                }
            )
        }
        
        composable("register") {
            RegisterScreen(
                onRegisterSuccess = {
                    navController.navigate("home") {
                        popUpTo("register") { inclusive = true }
                    }
                },
                onNavigateToLogin = {
                    navController.navigate("login")
                }
            )
        }
        
        composable("home") {
            val selectedItem = remember { mutableStateOf<BottomNavItem>(BottomNavItem.Home) }
            
            Column {
                Box(modifier = Modifier.weight(1f)) {
                    // Aquí iría el contenido principal de la pantalla
                }
                
                BottomNavigationBar(
                    selectedItem = selectedItem.value,
                    onItemSelected = { item ->
                        selectedItem.value = item
                        when (item) {
                            BottomNavItem.Profile -> navController.navigate("profile")
                            else -> {} // Manejar otras opciones de navegación
                        }
                    }
                )
            }
        }
        
        composable("profile") {
            val selectedItem = remember { mutableStateOf<BottomNavItem>(BottomNavItem.Profile) }
            
            Column {
                Box(modifier = Modifier.weight(1f)) {
                    ProfileScreen(
                        onLogout = {
                            navController.navigate("login") {
                                popUpTo(0) { inclusive = true }
                            }
                        }
                    )
                }
                
                BottomNavigationBar(
                    selectedItem = selectedItem.value,
                    onItemSelected = { item ->
                        selectedItem.value = item
                        when (item) {
                            BottomNavItem.Home -> navController.navigate("home")
                            else -> {} // Manejar otras opciones de navegación
                        }
                    }
                )
            }
        }
    }
}