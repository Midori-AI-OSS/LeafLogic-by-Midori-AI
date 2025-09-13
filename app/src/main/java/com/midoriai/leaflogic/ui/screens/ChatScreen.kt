package com.midoriai.leaflogic.ui.screens

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.SmartToy
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

data class ChatMessage(
    val text: String,
    val isFromUser: Boolean,
    val timestamp: String = ""
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen() {
    var messageText by remember { mutableStateOf("") }
    
    // Sample chat messages
    val chatMessages = remember {
        mutableListOf(
            ChatMessage(
                text = "Hello! I'm your AI nutrition assistant. How can I help you improve your eating habits today?",
                isFromUser = false,
                timestamp = "Just now"
            ),
            ChatMessage(
                text = "I've been feeling tired lately. Could it be related to my diet?",
                isFromUser = true,
                timestamp = "Just now"
            ),
            ChatMessage(
                text = "Yes, fatigue can definitely be related to nutrition! Based on your recent food logs, I notice you might be low on iron and B vitamins. You've been eating well, but adding more leafy greens like spinach and lean red meat could help boost your energy levels. Also, are you drinking enough water?",
                isFromUser = false,
                timestamp = "Just now"
            ),
            ChatMessage(
                text = "I probably don't drink enough water. How much should I be drinking?",
                isFromUser = true,
                timestamp = "Just now"
            ),
            ChatMessage(
                text = "For your activity level and body weight, I'd recommend about 8-10 glasses (64-80 oz) of water per day. You can track this in your health metrics! Try starting your day with a glass of water and keeping a water bottle nearby as a reminder.",
                isFromUser = false,
                timestamp = "Just now"
            )
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Default.SmartToy,
                            contentDescription = "AI Assistant",
                            tint = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text(
                                "AI Nutrition Assistant",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                "Online",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Chat messages
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                reverseLayout = true
            ) {
                items(chatMessages.reversed()) { message ->
                    ChatMessageBubble(message = message)
                }
            }
            
            // Message input
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.Bottom
            ) {
                OutlinedTextField(
                    value = messageText,
                    onValueChange = { messageText = it },
                    placeholder = { Text("Ask about nutrition, recipes, or health tips...") },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(24.dp),
                    maxLines = 3
                )
                Spacer(modifier = Modifier.width(8.dp))
                IconButton(
                    onClick = {
                        if (messageText.isNotBlank()) {
                            // TODO: Add message to chat and get AI response
                            messageText = ""
                        }
                    }
                ) {
                    Icon(
                        Icons.Default.Send,
                        contentDescription = "Send",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}

@Composable
fun ChatMessageBubble(
    message: ChatMessage
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (message.isFromUser) Arrangement.End else Arrangement.Start
    ) {
        if (!message.isFromUser) {
            Icon(
                Icons.Default.SmartToy,
                contentDescription = "AI",
                modifier = Modifier.size(32.dp),
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.width(8.dp))
        }
        
        Card(
            modifier = Modifier.fillMaxWidth(0.85f),
            colors = CardDefaults.cardColors(
                containerColor = if (message.isFromUser) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.surfaceVariant
                }
            ),
            shape = RoundedCornerShape(
                topStart = 16.dp,
                topEnd = 16.dp,
                bottomStart = if (message.isFromUser) 16.dp else 4.dp,
                bottomEnd = if (message.isFromUser) 4.dp else 16.dp
            )
        ) {
            Column(
                modifier = Modifier.padding(12.dp)
            ) {
                Text(
                    text = message.text,
                    style = MaterialTheme.typography.bodyMedium,
                    color = if (message.isFromUser) {
                        MaterialTheme.colorScheme.onPrimary
                    } else {
                        MaterialTheme.colorScheme.onSurfaceVariant
                    }
                )
                if (message.timestamp.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = message.timestamp,
                        style = MaterialTheme.typography.bodySmall,
                        color = if (message.isFromUser) {
                            MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.7f)
                        } else {
                            MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
                        }
                    )
                }
            }
        }
        
        if (message.isFromUser) {
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                Icons.Default.Person,
                contentDescription = "User",
                modifier = Modifier.size(32.dp),
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}