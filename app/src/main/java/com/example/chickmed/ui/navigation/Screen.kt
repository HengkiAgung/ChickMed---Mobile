package com.example.chickmed.ui.navigation

sealed class Screen(val route: String) {
    data object Home : Screen("home")

    // Analysis
    data object DetailAnalysis : Screen("analysis")
    data object Reports : Screen("reports")

    // Schedule
    data object Schedule : Screen("schedule")


    // Article
    data object DetailArticle : Screen("detail/{id}") {
        fun createRoute(id: Int) = "detail/$id"
    }

    // Account
    data object Profile : Screen("profile")

    // Auth
    data object Login : Screen("login")
    data object Register : Screen("register")
    data object Welcome : Screen("welcome")
}