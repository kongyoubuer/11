package com.modernapp.gallery.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Category : Screen("category")
    object Detail : Screen("detail/{itemId}") {
        fun createRoute(itemId: Long) = "detail/$itemId"
    }
    object Settings : Screen("settings")
}
