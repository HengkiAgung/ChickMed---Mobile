package com.example.chickmed.ui.component.article

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.chickmed.ui.theme.ChickMedTheme

@Composable
fun ArticleItem(
    id: Int,
    title: String,
    image: String,
    date: String,
    onNavigateToDetailScreen: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),

        modifier = modifier
            .fillMaxWidth()
            .padding(
                top = 10.dp,
                bottom = 8.dp,
                start = 10.dp,
                end = 10.dp,
            )
            .clickable {
                onNavigateToDetailScreen(id)
            }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxWidth()
                .background(Color.White)
                .clickable {
                    onNavigateToDetailScreen(id)
                }
        ) {
            AsyncImage(
                model = image,
                contentDescription = "$title Image",
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .padding(4.dp)
                    .size(100.dp)
                    .clip(MaterialTheme.shapes.medium)
            )
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(
                    text = title,
                    fontSize = 15.sp,
                    color = MaterialTheme.colorScheme.onTertiaryContainer,
                    modifier = modifier.testTag("ArticleTitle")
                )
                Text(
                    text = date,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Light,
                    fontStyle = FontStyle.Italic,
                    color = MaterialTheme.colorScheme.onTertiaryContainer,
                    modifier = modifier
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ArticleItemPreview() {
    ChickMedTheme {
        ArticleItem(
            id = 1,
            title = "title",
            image = "https://www.thesaurus.com/e/wp-content/uploads/2021/11/20211104_articles_1000x700.png",
            date = "2023-01-01",
            onNavigateToDetailScreen = {},
        )
    }
}