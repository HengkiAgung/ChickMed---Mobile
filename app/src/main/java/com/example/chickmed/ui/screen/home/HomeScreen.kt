package com.example.chickmed.ui.screen.home

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.chickmed.R
import com.example.chickmed.activity.ArticleActivity
import com.example.chickmed.activity.DetailArticleActivity
import com.example.chickmed.data.di.Injection
import com.example.chickmed.ui.component.article.ArticleItem
import com.example.chickmed.ui.component.ButtonActionMenu
import com.example.chickmed.ui.component.respond.ErrorMessage
import com.example.chickmed.ui.component.respond.LoadingIndicator
import com.example.chickmed.ui.navigation.Screen
import com.example.chickmed.ui.screen.ViewModelFactory
import com.example.chickmed.ui.state.UiState
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Objects

@Composable
fun HomeScreen(
    name: String,
    image: String,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory.getInstance(LocalContext.current)
    ),
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val activity = LocalContext.current as Activity

    val file = context.createImageFile()
    val uri = FileProvider.getUriForFile(
        Objects.requireNonNull(context),
        context.packageName + ".provider", file
    )

    var capturedImageUri by remember {
        mutableStateOf<Uri>(Uri.EMPTY)
    }

    val cameraLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) {
            capturedImageUri = uri
        }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ){
        if (it)
        {
            Toast.makeText(context, "Permission Granted", Toast.LENGTH_SHORT).show()
            cameraLauncher.launch(uri)
        }
        else
        {
            Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
        }
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(android.graphics.Color.parseColor("#FFFFFF")),
                        Color(android.graphics.Color.parseColor("#FF8204")),
                    )
                )
            )
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, bottom = 50.dp, top = 50.dp)
        ) {
            AsyncImage(
                model = image,
                contentDescription = "Profile Image",
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .padding(4.dp)
                    .size(60.dp)
                    .clip(CircleShape)
            )
            Text(
                text = "Welcome !",
                style = MaterialTheme.typography.titleMedium,
            )
            Text(
                text = name,
            )
            Text(
                text = "How are you today?",
            )
        }
        Column(
            modifier = modifier
                .clip(
                    MaterialTheme.shapes.large.copy(
                        bottomEnd = CornerSize(0.dp),
                        bottomStart = CornerSize(0.dp)
                    )
                )
                .background(Color.White)
                .padding(start = 20.dp, top = 50.dp, end = 20.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                ButtonActionMenu(
                    text = "Check Up",
                    icon = R.drawable.stethoscope,
                    onClick = {
                        val permissionCheckResult =
                            ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)

                        if (permissionCheckResult == PackageManager.PERMISSION_GRANTED)
                        {
                            cameraLauncher.launch(uri)
                        }
                        else
                        {
                            permissionLauncher.launch(Manifest.permission.CAMERA)
                        }
                    },
                )
                ButtonActionMenu(
                    text = "Schedule",
                    icon = R.drawable.clock,
                    onClick = {
//                        navController.navigate(Screen.Schedule.route)
                    },
                )
                ButtonActionMenu(
                    text = "Articles",
                    icon = R.drawable.newspaper,
                    onClick = {
                        activity.startActivity(
                            Intent(
                                context,
                                ArticleActivity::class.java
                            )
                        )
                    },
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            viewModel.articles.collectAsState(initial = UiState.Loading).value.let { articles ->
                when (articles) {
                    is UiState.Loading -> {
                        LoadingIndicator()
                        viewModel.getArticles(1)
                    }

                    is UiState.Success -> {
                        Row {
                            Text(
                                text = "Article",
                                style = MaterialTheme.typography.titleMedium,
                                modifier = modifier
                                    .padding(start = 10.dp, top = 10.dp, bottom = 10.dp)
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            Text(
                                text = "See All",
                                style = MaterialTheme.typography.titleSmall,
                                modifier = modifier
                                    .padding(start = 10.dp, top = 10.dp, bottom = 10.dp)
                                    .clickable {
                                        activity.startActivity(
                                            Intent(
                                                context,
                                                ArticleActivity::class.java
                                            )
                                        )
                                    }
                            )
                        }
                        articles.data.map { article ->
                            ArticleItem(
                                title = article.title,
                                date = article.date,
                                image = article.image,
                                onNavigateToDetailScreen = { id ->
                                    activity.startActivity(
                                        Intent(context, DetailArticleActivity::class.java).putExtra(
                                            "id_article",
                                            id
                                        )
                                    )
                                },
                                id = article.id,
                            )
                        }
                    }

                    is UiState.Error -> {
                        ErrorMessage(message = articles.errorMessage)
                    }
                }
            }
        }
    }
}

fun Context.createImageFile(): File {
    val timeStamp = SimpleDateFormat("yyyy_MM_dd_HH:mm:ss").format(Date())
    val imageFileName = "JPEG_" + timeStamp + "_"
    val image = File.createTempFile(
        imageFileName,
        ".jpg",
        externalCacheDir
    )

    return image
}