package com.example.chickmed.ui.navigation

sealed class Screen(val route: String) {
    data object Home : Screen("home")

    // Analysis
    data object DetailAnalysis : Screen("analysis")
    data object Reports : Screen("reports")

    // Schedule
    data object Schedule : Screen("schedule")


    // Article
    data object Bookmark : Screen("cart")
    data object DetailArticle : Screen("detail/{id}") {
        fun createRoute(id: Int) = "detail/$id"
    }

    // Account
    data object Profile : Screen("profile")
}