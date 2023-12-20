package bangkit.product.chickmed.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import bangkit.product.chickmed.R

//HomeButton(
//text = "Find Disease",
//image = "https://images.pexels.com/photos/771742/pexels-photo-771742.jpeg",
//onClick = onFindDiseaseClick,
//modifier = Modifier.weight(1f)
//)
@Composable
fun ButtonActionMenu (
    text: String,
    icon: Int,
    onClick: () -> Unit,
) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable { onClick() }
    ) {
        Icon(
            painter = painterResource(icon),
            contentDescription = "Localized description",
            modifier = Modifier
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primary)
                .padding(10.dp)
                .size(25.dp),
            tint = Color.White,
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(text = text, style = MaterialTheme.typography.bodyMedium)
    }
}
