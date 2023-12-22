package bangkit.product.chickmed.ui.screen.consult

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

data class Message(val sender: String, val content: String, val timestamp: String, val isMe: Boolean = false)

@Composable
fun ConsultScreen() {
    var message by remember { mutableStateOf("") }
    var messages by remember { mutableStateOf(generateDummyData()) }
    val listState = rememberLazyListState()

    LaunchedEffect(messages) {
        // Scroll to the bottom of the list when messages change
        listState.scrollToItem(messages.size - 1)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp)
    ) {
        // Chat Messages
        LazyColumn(
            modifier = Modifier
                .weight(1f),
            state = listState
        ) {
            items(messages) { chat ->
                ChatItem(chat)
            }
        }

        // Compose Message
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Input Field
            OutlinedTextField(
                value = message,
                onValueChange = { message = it },
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp),
                label = { Text("Type a message") },
                keyboardActions = KeyboardActions(
                    onSend = {
                        if (message.isNotEmpty()) {
                            // Handle send button click
                            // For simplicity, we'll just add the message to the list
                            val newMessage = Message("You", message, "now", true)
                            messages = messages + newMessage
                            // Clear the input field
                            message = ""
                        }
                    }
                ),
            )

            // Send Button
            IconButton(
                onClick = {
                    if (message.isNotEmpty()) {
                        // Handle send button click
                        // For simplicity, we'll just add the message to the list
                        val newMessage = Message("You", message, "now", true)
                        messages = messages + newMessage
                        // Clear the input field
                        message = ""
                    }
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Send,
                    contentDescription = "Send"
                )
            }
        }
    }
}

@Composable
fun ChatItem(message: Message) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            if (!message.isMe) {
                Box(
                    modifier = Modifier
                        .size(42.dp)
                        .clip(MaterialTheme.shapes.small)
                        .background(Color.Gray)
                        .shadow(1.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
            } else {
                Spacer(modifier = Modifier.width(50.dp))
            }
            Box(
                modifier = Modifier
                    .weight(1f) // Takes the available width in the Row
                    .padding(8.dp)
                    .clip(MaterialTheme.shapes.small)
                    .border(1.dp, Color.Gray, MaterialTheme.shapes.small)
                    .shadow(1.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(8.dp)
                ) {
                    Text(
                        text = message.sender,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(4.dp))

                    // Set the max width for the content
                    Text(
                        text = message.content,
                        color = Color.Black,
                        modifier = Modifier.widthIn(max = 200.dp) // Set the maximum width
                    )

                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = message.timestamp,
                        color = Color.Gray
                    )
                }
            }

            if (message.isMe) {
                Spacer(modifier = Modifier.width(8.dp))
                Box(
                    modifier = Modifier
                        .size(42.dp)
                        .clip(MaterialTheme.shapes.small)
                        .background(Color.Gray)
                        .shadow(1.dp)
                )
            } else {
                Spacer(modifier = Modifier.width(50.dp))
            }
        }
    }
}

fun generateDummyData(): List<Message> {
    val senders = listOf("Dokter Hewan", "Peternak Ayam")
    val contents = listOf(
        Message(
            "Bot",
            "Halo, bagaimana keadaan ternak ayammu?",
            "20 minutes ago",
        ),
        Message(
            "You",
            "Saya khawatir karena ayam-ayam saya terlihat terlalu besar dan rentan terkena penyakit.",
            "15 minutes ago",
            true
        ),
        Message(
            "Dokter Hewan",
            "Apakah Anda memperhatikan gejala khusus atau perilaku aneh pada ayam-ayamu?",
            "10 minutes ago",
        ),
        Message(
            "You",
            "Saya baru saja melihat beberapa ayam tampak lesu dan kurang aktif.",
            "5 minutes ago",
            true
        ),
        Message(
            "Dokter Hewan",
            "Mungkin mereka mengalami masalah kesehatan. Apakah Anda memberikan makanan atau nutrisi khusus?",
            "3 minutes ago",
        ),
        Message(
            "You",
            "Saya memberikan pakan berkualitas tinggi, tetapi sepertinya tidak cukup.",
            "3 minutes ag",
            true
        ),
        Message(
            "Dokter Hewan",
            "Mungkin perlu dilakukan pemeriksaan lebih lanjut. Bagaimana dengan kondisi sanitasi kandang?",
            "2 minutes ago",
        ),
        Message(
            "You",
            "Kandang saya cukup bersih, tapi mungkin perlu peningkatan. Apa saran Anda untuk merawat ayam yang sudah besar?",
            "2 minutes ag",
            true
        ),
        Message(
            "Dokter Hewan",
            "Pertama, pastikan mereka mendapatkan cukup ruang untuk bergerak. Selain itu, periksa kembali jenis pakan dan takarannya.",
            "2 minutes ago",
        ),
        Message(
            "You",
            "Saya akan mencoba meningkatkan ruang gerak dan memperbarui jenis pakan. Apa lagi yang bisa saya lakukan?",
            "1 minutes ag",
            true
        ),
        Message(
            "Dokter Hewan",
            "Pastikan juga mereka mendapatkan vaksinasi yang sesuai. Segera hubungi saya jika ada perubahan lebih lanjut dalam kondisi kesehatan ayam-ayamu.",
            "1 minutes ag",
        ),
        Message(
            "You",
            "Terima kasih banyak atas saran Anda. Saya akan segera mengambil tindakan untuk meningkatkan kondisi ternak ayam saya.",
            "now",
            true
        ),
    )

    val messages = mutableListOf<Message>()
    contents.map {
        messages.add(it)
    }

    return messages
}