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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Text(
                        "LeafLogic",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold
                    )
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* TODO: Quick log action */ }
            ) {
                Icon(Icons.Default.Add, contentDescription = "Quick Log")
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                WelcomeCard()
            }
            
            item {
                Text(
                    "Today's Overview",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
            
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    QuickStatCard(
                        modifier = Modifier.weight(1f),
                        title = "Calories",
                        value = "1,250",
                        target = "/ 2,000",
                        icon = Icons.Default.TrendingUp
                    )
                    QuickStatCard(
                        modifier = Modifier.weight(1f),
                        title = "Water",
                        value = "6",
                        target = "/ 8 glasses",
                        icon = Icons.Default.WaterDrop
                    )
                }
            }
            
            item {
                Text(
                    "Recent Activities",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
            
            item {
                RecentActivityCard(
                    activity = "Logged breakfast",
                    time = "8:30 AM",
                    details = "Oatmeal with berries • 350 cal"
                )
            }
            
            item {
                RecentActivityCard(
                    activity = "LLM Nutrition Tip",
                    time = "Yesterday",
                    details = "Try adding more leafy greens to boost iron intake"
                )
            }
        }
    }
}

@Composable
fun WelcomeCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                "Good morning! 🌱",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "Ready to make healthy choices today? Let's track your nutrition and get personalized advice from your LLM nutrition assistant.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
    }
}

@Composable
fun QuickStatCard(
    modifier: Modifier = Modifier,
    title: String,
    value: String,
    target: String,
    icon: ImageVector
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                icon,
                contentDescription = title,
                modifier = Modifier.size(24.dp),
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                title,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Row(
                verticalAlignment = Alignment.Baseline
            ) {
                Text(
                    value,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    target,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
fun RecentActivityCard(
    activity: String,
    time: String,
    details: String
) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Text(
                    activity,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    time,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                details,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}