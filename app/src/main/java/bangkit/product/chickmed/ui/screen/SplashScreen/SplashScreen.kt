package bangkit.product.chickmed.ui.screen.SplashScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import bangkit.product.chickmed.R

@Composable
fun SplashScreen () {
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
//            Image(
//                painter = painterResource(id = R.drawable.logo_title), // Replace with your app logo
//                contentDescription = null,
//                contentScale = ContentScale.Crop,
//                modifier = Modifier
//                    .size(300.dp)
//                    .align(Alignment.CenterHorizontally)
////                    .clip(CircleShape)
//            )

//            Text(
//                text = "Sedang mengecek data anda",
//                style = MaterialTheme.typography.titleMedium,
//                color = MaterialTheme.colorScheme.primary
//            )
//            Text(
//                text = "mohon tunggu...",
//            )
        }
    }
}