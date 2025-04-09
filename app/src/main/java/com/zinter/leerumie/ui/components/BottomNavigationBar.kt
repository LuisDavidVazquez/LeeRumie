package com.zinter.leerumie.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.People
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview

sealed class BottomNavItem(var title: String, var icon: ImageVector, var screenRoute: String) {
    object Home : BottomNavItem("Inicio", Icons.Filled.Home, "home")
    object Friends : BottomNavItem("Amigos", Icons.Filled.People, "friends")
    object Notifications : BottomNavItem("Notificaciones", Icons.Filled.Notifications, "notifications")
    object Profile : BottomNavItem("Perfil", Icons.Filled.Person, "profile")
}

@Composable
fun BottomNavigationBar(
    selectedItem: BottomNavItem,
    onItemSelected: (BottomNavItem) -> Unit
) {
    NavigationBar {
        val items = listOf(
            BottomNavItem.Home,
            BottomNavItem.Friends,
            BottomNavItem.Notifications,
            BottomNavItem.Profile
        )
        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.title) },
                label = { Text(text = item.title) },
                selected = selectedItem == item,
                onClick = { onItemSelected(item) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.Blue,
                    unselectedIconColor = Color.Gray,
                    selectedTextColor = Color.Blue,
                    unselectedTextColor = Color.Gray
                )
            )
        }
    }
}

@Preview
@Composable
fun BottomNavigationBarPreview() {
    BottomNavigationBar(selectedItem = BottomNavItem.Home, onItemSelected = {})
} 