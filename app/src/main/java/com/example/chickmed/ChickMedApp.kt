package com.example.chickmed

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.chickmed.ui.component.BottomBar
import com.example.chickmed.ui.navigation.Screen
import com.example.chickmed.ui.screen.Auth.AuthViewModel
import com.example.chickmed.ui.screen.Auth.LoginScreen
import com.example.chickmed.ui.screen.Auth.RegisterScreen
import com.example.chickmed.ui.screen.Auth.WelcomeScreen
import com.example.chickmed.ui.screen.SplashScreen.SplashScreen
import com.example.chickmed.ui.screen.ViewModelFactory
import com.example.chickmed.ui.screen.account.change_password.ChangePasswordScreen
import com.example.chickmed.ui.screen.account.my_account.MyAccountScreen
import com.example.chickmed.ui.screen.account.profile.ProfileScreen
import com.example.chickmed.ui.screen.analysis.report.ReportsScreen
import com.example.chickmed.ui.screen.home.HomeScreen
import com.example.chickmed.ui.screen.schedule.ScheduleScreen
import com.example.chickmed.ui.state.UiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChickMedApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    viewModel: AppViewModel = viewModel(
        factory = ViewModelFactory.getInstance(LocalContext.current)
    ),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    var message by remember { mutableStateOf("") }
    val redirectToWelcome = { messageParam: String ->
        message = messageParam
        navController.navigate(Screen.Welcome.route) {
            popUpTo(navController.graph.id) {
                inclusive = true
            }
        }
    }

    val checkToken by viewModel.isHaveToken

    LaunchedEffect(key1 = checkToken) {
        viewModel.checkToken()
    }

    when (checkToken) {
        is UiState.Loading -> {
            SplashScreen()
        }

        is UiState.Success -> {
            Scaffold(
                topBar = {
                    if (currentRoute == Screen.MyAccount.route || currentRoute == Screen.ChangePassword.route) {
                        TopAppBar(
                            title = {
                                Text(
                                    text = if(currentRoute == Screen.MyAccount.route) "Detail Account" else "Change Password",
                                    style = MaterialTheme.typography.titleMedium,
                                )
                            },
                            navigationIcon = {
                                IconButton(onClick = {
                                    navController.popBackStack()
                                }) {
                                    Icon(
                                        imageVector = Icons.Filled.ArrowBack,
                                        contentDescription = "Back"
                                    )
                                }
                            }

                        )
                    }
                },
                bottomBar =
                {
                    if (currentRoute != Screen.Login.route && currentRoute != Screen.Welcome.route && currentRoute != Screen.Register.route && currentRoute != null) {
                        BottomBar(navController)
                    }
                },
                modifier = modifier
            )
            { innerPadding ->
                NavHost(
                    navController = navController,
                    startDestination = if ((checkToken as UiState.Success<Boolean>).data) {
                        Screen.Home.route
                    } else {
                        Screen.Welcome.route
                    },
                    modifier = Modifier.padding(innerPadding)
                ) {
                    composable(Screen.Home.route) {
                        HomeScreen(
                            redirectToWelcome = { redirectToWelcome("Session is expired") },
                            redirectToReport = {
                                navController.navigate(Screen.Reports.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    restoreState = true
                                    launchSingleTop = true
                                }
                            },
                        )
                    }
                    composable(Screen.Reports.route) {
                        ReportsScreen(
                            redirectToWelcome = { redirectToWelcome("Session is expired") }
                        )
                    }
                    composable(Screen.Schedule.route) {
                        ScheduleScreen(
                            redirectToWelcome = { redirectToWelcome("Session is expired") }
                        )
                    }
                    composable(Screen.Profile.route) {
                        ProfileScreen(
                            redirectToWelcome = { redirectToWelcome("") },
                            redirectToMyAccount = { navController.navigate(Screen.MyAccount.route) },
                            redirectToChangePassword = { navController.navigate(Screen.ChangePassword.route) }
                        )
                    }
                    composable(Screen.MyAccount.route) {
                        MyAccountScreen(
                            redirectToWelcome = { redirectToWelcome("Session is expired") },
                        )
                    }
                    composable(Screen.ChangePassword.route) {
                        ChangePasswordScreen(
                            redirectToWelcome = { redirectToWelcome("Session is expired") },
                        )
                    }
                    composable(Screen.Welcome.route) {
                        WelcomeScreen(navController = navController, message = message)
                    }
                    composable(Screen.Login.route) {
                        LoginScreen(navController = navController, redirectToHome = {
                            navController.navigate(Screen.Home.route) {
                                popUpTo(navController.graph.id) {
                                    inclusive = true
                                }
                            }
                        })
                    }
                    composable(Screen.Register.route) {
                        RegisterScreen(navController = navController, redirectToHome = {
                            navController.navigate(Screen.Home.route) {
                                popUpTo(navController.graph.id) {
                                    inclusive = true
                                }
                            }
                        })
                    }
                }
            }
        }

        else -> {}
    }
}

