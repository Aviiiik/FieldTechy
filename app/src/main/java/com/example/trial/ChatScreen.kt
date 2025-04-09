package com.example.trial

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachFile
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen() {
    val chatMessages = listOf(
        ChatMessage(
            "loading (it's faked but the same idea applies) 👉 https://github.com/android/compose-samples/tree/master/JetNews",
            "System",
            "",
            MessageType.SYSTEM
        ),
        ChatMessage(
            "@allcomposers Take a look at the Flow.collectAsStateWithLifecycle() APIs",
            "Taylor Brooks",
            "8:05 PM",
            MessageType.OTHER
        ),
        ChatMessage(
            "You can use all the same stuff",
            "Taylor Brooks",
            "",
            MessageType.OTHER
        ),
        ChatMessage(
            "Thank you!",
            "me",
            "8:06 PM",
            MessageType.SELF
        ),
        ChatMessage(
            "",
            "me",
            "",
            MessageType.SELF_IMAGE
        ),
        ChatMessage(
            "Check it out!",
            "me",
            "",
            MessageType.SELF
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {
        // Top Bar
        TopAppBar(
            title = {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = android.R.drawable.ic_dialog_info),
                        contentDescription = "Group Icon",
                        modifier = Modifier.size(24.dp),
                        tint = Color(0xFF5C6BC0)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text(
                            text = "#composers",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                        Text(
                            text = "42 members",
                            fontSize = 12.sp,
                            color = Color.Gray
                        )
                    }
                }
            },
            actions = {
                IconButton(onClick = { /* Search action */ }) {
                    Icon(Icons.Default.Search, contentDescription = "Search")
                }
                IconButton(onClick = { /* Info action */ }) {
                    Icon(Icons.Default.Info, contentDescription = "Info")
                }
            }
        )

        // Chat Messages
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            items(chatMessages) { message ->
                MessageItem(message)
                Spacer(modifier = Modifier.height(8.dp))
            }
            item {
                Text(
                    text = "Today",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    color = Color.Gray,
                    fontSize = 12.sp
                )
            }
        }

        // Bottom Bar
        BottomBar()
    }
}

@Composable
fun MessageItem(message: ChatMessage) {
    when (message.type) {
        MessageType.SYSTEM -> SystemMessage(message)
        MessageType.OTHER -> OtherUserMessage(message)
        MessageType.SELF -> SelfMessage(message)
        MessageType.SELF_IMAGE -> SelfImageMessage()
    }
}

@Composable
fun SystemMessage(message: ChatMessage) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = message.content,
            color = Color.Gray,
            fontSize = 14.sp,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
fun OtherUserMessage(message: ChatMessage) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top
    ) {
        // Profile picture
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(Color.Gray)
        ) {
            // In a real app, load the user's profile picture here
        }

        Spacer(modifier = Modifier.width(8.dp))

        Column {
            if (message.timestamp.isNotEmpty()) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = message.sender,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = message.timestamp,
                        color = Color.Gray,
                        fontSize = 12.sp
                    )
                }
            }

            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(Color.White),
                elevation = CardDefaults.cardElevation(1.dp)
            ) {
                Text(
                    text = message.content,
                    modifier = Modifier.padding(12.dp),
                    fontSize = 15.sp
                )
            }
        }
    }
}

@Composable
fun SelfMessage(message: ChatMessage) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(Color(0xFF5C6BC0))
        ) {
            Text(
                text = message.content,
                modifier = Modifier.padding(12.dp),
                color = Color.White,
                fontSize = 15.sp
            )
        }
    }
}

@Composable
fun SelfImageMessage() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(Color(0xFF5C6BC0)),
            modifier = Modifier.size(150.dp)
        ) {
            // In a real app, load the actual image
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFF30A4D9))
            ) {
                Text(
                    text = "THANK YOU!",
                    modifier = Modifier.align(Alignment.Center),
                    color = Color.Yellow,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }
        }
    }
}

@Composable
fun BottomBar() {
    Surface {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
        ) {
            Divider()

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Message #composers",
                    color = Color.Gray,
                    modifier = Modifier.weight(1f)
                )

                IconButton(onClick = { /* Send action */ }) {
                    Icon(
                        imageVector = Icons.Default.Send,
                        contentDescription = "Send",
                        tint = Color.Gray
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { /* Emoji action */ }) {
                    Icon(
                        imageVector = Icons.Default.Face,
                        contentDescription = "Emoji",
                        tint = Color.Gray
                    )
                }

                IconButton(onClick = { /* Mention action */ }) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Mention",
                        tint = Color.Gray
                    )
                }

                IconButton(onClick = { /* Attachment action */ }) {
                    Icon(
                        imageVector = Icons.Default.AttachFile,
                        contentDescription = "Attachment",
                        tint = Color.Gray
                    )
                }

                IconButton(onClick = { /* Image action */ }) {
                    Icon(
                        imageVector = Icons.Default.Image,
                        contentDescription = "Image",
                        tint = Color.Gray
                    )
                }

                IconButton(onClick = { /* More actions */ }) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "More",
                        tint = Color.Gray
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                TextButton(
                    onClick = { /* Send action */ },
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = Color.Gray
                    )
                ) {
                    Text("Send")
                }
            }
        }
    }
}

data class ChatMessage(
    val content: String,
    val sender: String,
    val timestamp: String,
    val type: MessageType
)

enum class MessageType {
    SYSTEM, OTHER, SELF, SELF_IMAGE
}

@Preview(showBackground = true)
@Composable
fun ChatScreenPreview() {
    ChatScreen()
}