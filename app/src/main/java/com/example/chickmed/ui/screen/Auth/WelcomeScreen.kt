package com.example.chickmed.ui.screen.Auth

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.chickmed.R
import com.example.chickmed.ui.navigation.Screen
import com.example.chickmed.ui.theme.ChickMedTheme

@Composable
fun WelcomeScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_title), // Replace with your app logo
                contentDescription = null,
                modifier = Modifier
                    .size(300.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = "Let’s get started!",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = modifier
                    .padding(start = 10.dp, top = 10.dp, bottom = 10.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Login to Keep",
                style = MaterialTheme.typography.titleMedium,
                modifier = modifier
                    .padding(start = 10.dp, top = 10.dp, bottom = 10.dp)
            )
            Text(
                text = "Your Chick Healthy and Fit",
                style = MaterialTheme.typography.titleMedium,
                color = Color.Gray,
                modifier = modifier
                    .padding(start = 10.dp, bottom = 10.dp)
            )
            Spacer(modifier = Modifier.height(30.dp))

            Button(
                onClick = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                },
                modifier = Modifier
                    .width(250.dp)
                    .height(56.dp)
            ) {
                Text("Sign In")
            }
            Spacer(modifier = Modifier.height(30.dp))
            Button(
                onClick = {
                    navController.navigate(Screen.Register.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White
                ),
                border = BorderStroke(1.dp, Color(0xFFFF8204)),
                modifier = Modifier
                    .width(250.dp)
                    .height(56.dp)
            ) {
                Text("Sign Up", color = Color(0xFFFF8204))
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun WelcomeScreenPreview() {
    ChickMedTheme {
        WelcomeScreen()
    }
}