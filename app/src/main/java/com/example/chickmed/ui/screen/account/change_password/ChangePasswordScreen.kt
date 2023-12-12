package com.example.chickmed.ui.screen.account.change_password

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.chickmed.ui.screen.ViewModelFactory
import com.example.chickmed.ui.state.UiState

@Composable
fun ChangePasswordScreen (
    redirectToWelcome: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ChangePasswordViewModel = viewModel(
        factory = ViewModelFactory.getInstance(LocalContext.current)
    ),
) {
    var password by remember { mutableStateOf("") }
    var confirm_password by remember { mutableStateOf("") }
    var submit by remember { mutableStateOf("Submit") }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
        ) {
            viewModel.isChange.collectAsState().value.let { isChange ->
                when (isChange) {
                    is UiState.Success -> {
                        submit = "Submit"
                        if (isChange.data) {
                            Text(
                                text =  "Password changed successfully",
                                style = MaterialTheme.typography.titleMedium,
                                color = Color.Green
                            )
                        } else {
                            Text(
                                text =  "Server error.",
                                style = MaterialTheme.typography.titleMedium,
                                color = Color.Green
                            )
                        }

                    }
                    is UiState.Error -> {
                        submit = "Submit"
                        Text(
                            text =  isChange.errorMessage,
                            style = MaterialTheme.typography.titleMedium,
                            color = Color.Red
                        )
                    }
                    is UiState.Unauthorized -> {
                        redirectToWelcome()
                    }
                    else -> {}
                }
            }

            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("New Password") },
                leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = null) },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = confirm_password,
                onValueChange = { confirm_password = it },
                label = { Text("Confirm New Password") },
                leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = null) },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (submit != "Loading...") {
                        submit = "Loading..."
                        viewModel.changePassword(password, confirm_password)
                        password = ""
                        confirm_password = ""
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text(submit)
            }
        }
    }
}