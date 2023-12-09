package com.example.chickmed

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.chickmed.ui.component.BottomBar
import com.example.chickmed.ui.navigation.Screen
import com.example.chickmed.ui.screen.Auth.LoginScreen
import com.example.chickmed.ui.screen.Auth.RegisterScreen
import com.example.chickmed.ui.screen.Auth.WelcomeScreen
import com.example.chickmed.ui.screen.account.profile.ProfileScreen
import com.example.chickmed.ui.screen.analysis.report.ReportsScreen
import com.example.chickmed.ui.screen.home.HomeScreen
import com.example.chickmed.ui.screen.schedule.ScheduleScreen

@Composable
fun ChickMedApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar =
        {
            if (currentRoute != Screen.Login.route && currentRoute != Screen.Welcome.route && currentRoute != Screen.Register.route) {
                BottomBar(navController)
            }
        },
        modifier = modifier
    )
    { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    image = "https://images.pexels.com/photos/771742/pexels-photo-771742.jpeg",
                    name = "Hengki Agung Prayoga",
                )
            }
            composable(Screen.Reports.route) {
                ReportsScreen()
            }
            composable(Screen.Schedule.route) {
                ScheduleScreen(
                    onScheduleClick = {}
                )
            }
            composable(Screen.Profile.route) {
                ProfileScreen()
            }
            composable(Screen.Welcome.route) {
                WelcomeScreen(navController = navController)
            }
            composable(Screen.Login.route) {
                LoginScreen(navController = navController)
            }
            composable(Screen.Register.route) {
                RegisterScreen(navController = navController)
            }
        }
    }
}