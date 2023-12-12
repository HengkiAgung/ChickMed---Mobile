package com.example.chickmed.ui.screen.account.my_account

import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.core.net.toFile
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import coil.compose.AsyncImage
import com.example.chickmed.data.model.UserModel
import com.example.chickmed.ui.component.respond.ErrorMessage
import com.example.chickmed.ui.component.respond.LoadingIndicator
import com.example.chickmed.ui.navigation.Screen
import com.example.chickmed.ui.screen.ViewModelFactory
import com.example.chickmed.ui.state.UiState
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

@Composable
fun MyAccountScreen(
    redirectToWelcome: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MyAccountViewModel = viewModel(
        factory = ViewModelFactory.getInstance(LocalContext.current)
    ),
) {
    var submit by remember { mutableStateOf("Submit") }
    var error by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var user by remember { mutableStateOf(UserModel(0, "", "", "", "")) }
    var message by remember { mutableStateOf("") }

    val context = LocalContext.current
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val bitMap = remember { mutableStateOf<Bitmap?>(null) }
    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) {
            imageUri = it
        }

    fun createCustomTempFile(context: Context): File {
        val filesDir = context.externalCacheDir
        return File.createTempFile("profile", ".jpg", filesDir)
    }

    fun uriToFile(uri: Uri): File {
        val myFile = createCustomTempFile(context)
        val inputStream = context.contentResolver.openInputStream(uri) as InputStream
        val outputStream = FileOutputStream(myFile)
        val buffer = ByteArray(1024)
        var length: Int
        while (inputStream.read(buffer).also { length = it } > 0) outputStream.write(buffer, 0, length)
        outputStream.close()
        inputStream.close()

        return myFile

    }

    LaunchedEffect(key1 = user) {
        viewModel.getUser()
    }

    viewModel.user.collectAsState(initial = UiState.Loading).value.let { respond ->
        when (respond) {
            is UiState.Loading -> {
                LoadingIndicator()
            }

            is UiState.Success -> {
                user = respond.data
                if (submit == "Loading...") message = "Update Success"
                submit = "Submit"

                DisposableEffect(key1 = user){
                    if (name == "") name = user.name
                    if (email == "") email = user.email

                    onDispose { }
                }
            }

            is UiState.Error -> {
                error = respond.errorMessage
                submit = "Submit"
            }

            is UiState.Unauthorized -> {
                DisposableEffect(key1 = respond) {
                    when (respond) {
                        is UiState.Unauthorized -> {
                            redirectToWelcome()
                        }

                        else -> {}
                    }
                    onDispose { }
                }
            }
        }
    }

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
            Text(
                text =  message,
                style = MaterialTheme.typography.titleMedium,
                color = Color.Green
            )
            Text(
                text = error,
                style = MaterialTheme.typography.titleMedium,
                color = Color.Red
            )

            imageUri?.let {
                if (Build.VERSION.SDK_INT < 28) {
                    bitMap.value = MediaStore.Images.Media.getBitmap(
                        context.contentResolver,
                        imageUri
                    )
                } else {
                    val source =
                        ImageDecoder.createSource(context.contentResolver, imageUri!!)
                    bitMap.value = ImageDecoder.decodeBitmap(source)
                }

                bitMap.value?.let {
                    Image(
                        bitmap = it.asImageBitmap(),
                        contentDescription = "Profile Image",
                        contentScale = ContentScale.Crop,
                        modifier = modifier
                            .padding(4.dp)
                            .size(60.dp)
                            .clip(CircleShape)
                            .clickable {
                                launcher.launch("image/*")
                            }
                    )
                }
            }

            if (imageUri == null) {
                AsyncImage(
                    model = if(user.profile.isNotEmpty()) user.profile else "https://www.its.ac.id/international/wp-content/uploads/sites/66/2020/02/blank-profile-picture-973460_1280-1.jpg",
                    contentDescription = "Profile Image",
                    contentScale = ContentScale.Crop,
                    modifier = modifier
                        .padding(4.dp)
                        .size(60.dp)
                        .clip(CircleShape)
                        .clickable {
                            launcher.launch("image/*")
                        }
                )
            }
            TextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Name") },
                leadingIcon = { Icon(Icons.Filled.Person, contentDescription = null) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                leadingIcon = { Icon(Icons.Filled.Person, contentDescription = null) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (submit != "Loading..." ) {
                        submit = "Loading..."
                        message = ""
                        error = ""
                        if (imageUri == null){
                            viewModel.updateUser(
                                name = name,
                                profile = null,
                                email = email,
                            )
                        } else {
                            viewModel.updateUser(
                                name,
                                uriToFile(imageUri!!),
                                email,
                            )
                        }
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
