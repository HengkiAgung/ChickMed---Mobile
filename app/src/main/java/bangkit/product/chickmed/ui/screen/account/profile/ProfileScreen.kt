package bangkit.product.chickmed.ui.screen.account.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import coil.compose.AsyncImage
import bangkit.product.chickmed.R
import bangkit.product.chickmed.ui.component.respond.ErrorMessage
import bangkit.product.chickmed.ui.component.respond.LoadingIndicator
import bangkit.product.chickmed.ui.screen.ViewModelFactory
import bangkit.product.chickmed.ui.screen.article.article.ArticleViewModel
import bangkit.product.chickmed.ui.state.UiState

@Composable
fun ProfileScreen(
    redirectToWelcome: (String) -> Unit,
    redirectToMyAccount: () -> Unit,
    redirectToChangePassword: () -> Unit,
    viewModel: ProfileViewModel = viewModel(
        factory = ViewModelFactory.getInstance(LocalContext.current)
    ),
    modifier: Modifier = Modifier
) {
    viewModel.user.collectAsState(initial = UiState.Loading).value.let { user ->
        when (user) {
            is UiState.Loading -> {
                LoadingIndicator(modifier = modifier)
                viewModel.getUser()
            }

            is UiState.Success -> {
                LazyColumn(
                    modifier = modifier
                        .padding(20.dp)
                        .fillMaxSize()
                ) {
                    item {
                        user.data.apply {
                            ElevatedCard(
                                elevation = CardDefaults.cardElevation(
                                    defaultElevation = 4.dp
                                ),

                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        redirectToMyAccount()
                                    }
                            ) {
                                Row (
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = modifier
                                        .fillMaxWidth()
                                        .background(Color.White)
                                        .padding(20.dp)
                                ){
                                    AsyncImage(
                                        model = profile,
                                        contentDescription = "Profile Image",
                                        contentScale = ContentScale.Crop,
                                        modifier = modifier
                                            .padding(4.dp)
                                            .size(60.dp)
                                            .clip(CircleShape)
                                    )
                                    Spacer(modifier = modifier.width(20.dp))
                                    Column {
                                        Text(
                                            text = name,
                                            style = MaterialTheme.typography.titleMedium,
                                        )
                                        Text(
                                            text = email,
                                            style = MaterialTheme.typography.titleMedium,
                                            color = Color.Gray
                                        )
                                    }
                                    Spacer(modifier = modifier.weight(1f))
                                    Icon(
                                        imageVector = Icons.Default.Edit,
                                        contentDescription = "Edit Button",
                                    )
                                }
                            }
                        }
                    }

                    item {
                        user.data.apply {
                            Spacer(modifier = modifier.height(20.dp))
                            ElevatedCard(
                                elevation = CardDefaults.cardElevation(
                                    defaultElevation = 6.dp
                                ),

                                modifier = modifier
                                    .fillMaxWidth()
                            ) {
                                Column {
                                    Row (
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = modifier
                                            .fillMaxWidth()
                                            .background(Color.White)
                                            .padding(20.dp)
                                            .clickable {
                                                redirectToMyAccount()
                                            }
                                    ){
                                        Icon(
                                            imageVector = Icons.Default.Person,
                                            contentDescription = "My Account",
                                        )
                                        Spacer(modifier = modifier.width(20.dp))
                                        Column {
                                            Text(
                                                text = "My Account",
                                                style = MaterialTheme.typography.titleMedium,
                                            )
                                            Text(
                                                text = "Make change to your acccount",
                                                style = MaterialTheme.typography.bodySmall,
                                            )
                                        }
                                        Spacer(modifier = modifier.weight(1f))
                                        Icon(
                                            imageVector = Icons.Default.KeyboardArrowRight,
                                            contentDescription = "",
                                        )
                                    }

                                    Row (
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = modifier
                                            .fillMaxWidth()
                                            .background(Color.White)
                                            .padding(20.dp)
                                            .clickable {
                                                redirectToChangePassword()
                                            }
                                    ){
                                        Icon(
                                            imageVector = Icons.Default.Lock,
                                            contentDescription = "Change Password",
                                        )
                                        Spacer(modifier = modifier.width(20.dp))
                                        Column {
                                            Text(
                                                text = "Change Password",
                                                style = MaterialTheme.typography.titleMedium,
                                            )
                                            Text(
                                                text = "Further secure your account for safety",
                                                style = MaterialTheme.typography.bodySmall,
                                            )
                                        }
                                        Spacer(modifier = modifier.weight(1f))
                                        Icon(
                                            imageVector = Icons.Default.KeyboardArrowRight,
                                            contentDescription = "",
                                        )
                                    }

                                    Row (
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = modifier
                                            .fillMaxWidth()
                                            .background(Color.White)
                                            .padding(20.dp)
                                            .clickable {
                                                viewModel.logout()
                                                redirectToWelcome("")
                                            },
                                    ){
                                        Icon(
                                            imageVector = ImageVector.vectorResource(id = R.drawable.baseline_logout),
                                            contentDescription = "Log Out",
                                        )
                                        Spacer(modifier = modifier.width(20.dp))
                                        Column {
                                            Text(
                                                text = "Log Out",
                                                style = MaterialTheme.typography.titleMedium,
                                            )
                                            Text(
                                                text = "Further secure your account for safety",
                                                style = MaterialTheme.typography.bodySmall,
                                            )
                                        }
                                        Spacer(modifier = modifier.weight(1f))
                                        Icon(
                                            imageVector = Icons.Default.KeyboardArrowRight,
                                            contentDescription = "",
                                            modifier = modifier.clickable { },
                                        )
                                    }
                                }
                            }
                        }
                    }

                    item {
                        Spacer(modifier = modifier.height(40.dp))
                        Text(
                            text = "More",
                            style = MaterialTheme.typography.titleMedium,
                        )
                        Spacer(modifier = modifier.height(20.dp))
                        ElevatedCard(
                            elevation = CardDefaults.cardElevation(
                                defaultElevation = 6.dp
                            ),

                            modifier = modifier
                                .fillMaxWidth()
                        ) {
                            Column {
                                Row (
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = modifier
                                        .fillMaxWidth()
                                        .background(Color.White)
                                        .padding(20.dp)
                                ){
                                    Icon(
                                        imageVector = ImageVector.vectorResource(id = R.drawable.baseline_web),
                                        contentDescription = "Website",
                                    )
                                    Spacer(modifier = modifier.width(20.dp))
                                    Column {
                                        Text(
                                            text = "Website",
                                            style = MaterialTheme.typography.titleMedium,
                                        )
                                        Text(
                                            text = "Our website for more information",
                                            style = MaterialTheme.typography.bodySmall,
                                        )
                                    }
                                    Spacer(modifier = modifier.weight(1f))
                                    Icon(
                                        imageVector = Icons.Default.KeyboardArrowRight,
                                        contentDescription = "",
                                    )
                                }

                                Row (
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = modifier
                                        .fillMaxWidth()
                                        .background(Color.White)
                                        .padding(20.dp)
                                ){
                                    Icon(
                                        imageVector = Icons.Default.Call,
                                        contentDescription = "Help & Support",
                                    )
                                    Spacer(modifier = modifier.width(20.dp))
                                    Column {
                                        Text(
                                            text = "Help & Supportte",
                                            style = MaterialTheme.typography.titleMedium,
                                        )
                                        Text(
                                            text = "We are ready to help",
                                            style = MaterialTheme.typography.bodySmall,
                                        )
                                    }
                                    Spacer(modifier = modifier.weight(1f))
                                    Icon(
                                        imageVector = Icons.Default.KeyboardArrowRight,
                                        contentDescription = "",
                                    )
                                }
                            }
                        }
                    }
                }
            }

            is UiState.Error -> {
                ErrorMessage(message = user.errorMessage, modifier = modifier)
            }

            is UiState.Unauthorized -> {
                redirectToWelcome("Session is expired")
            }
        }
    }
}