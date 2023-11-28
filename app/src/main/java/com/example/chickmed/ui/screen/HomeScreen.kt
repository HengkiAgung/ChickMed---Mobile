package com.example.chickmed.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.chickmed.ui.theme.ChickMedTheme

@Composable
fun HomeScreen(
    image: String,
    onFindDiseaseClick: () -> Unit = {},
    onScheduleClick: () -> Unit = {},
    onInfoDiseaseClick: () -> Unit = {},
    onArticleClick: (Int) -> Unit = {},
    modifier: Modifier = Modifier
) {
    Column {
        AsyncImage(
            model = image,
            contentDescription = "Profile Image",
            contentScale = ContentScale.Crop,
            modifier = modifier
                .padding(4.dp)
                .size(100.dp)
                .clip(MaterialTheme.shapes.medium)
        )
        Text(
            text = "Find Disease",
            style = MaterialTheme.typography.titleMedium,
        )

    }
}