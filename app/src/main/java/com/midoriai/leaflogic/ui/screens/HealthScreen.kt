package com.midoriai.leaflogic.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.MonitorWeight
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
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

data class HealthMetric(
    val title: String,
    val value: String,
    val unit: String,
    val icon: ImageVector,
    val progress: Float = 0f,
    val target: String? = null
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HealthScreen() {
    val healthMetrics = listOf(
        HealthMetric(
            title = "Weight",
            value = "68.5",
            unit = "kg",
            icon = Icons.Default.MonitorWeight,
            target = "Goal: 65 kg"
        ),
        HealthMetric(
            title = "Steps Today",
            value = "8,234",
            unit = "steps",
            icon = Icons.Default.FitnessCenter,
            progress = 0.82f,
            target = "Goal: 10,000"
        ),
        HealthMetric(
            title = "Weekly Avg",
            value = "7,650",
            unit = "steps/day",
            icon = Icons.Default.TrendingUp
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Text(
                        "Health Metrics",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold
                    )
                }
            )
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
                Text(
                    "Today's Progress",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
            
            items(healthMetrics.size) { index ->
                HealthMetricCard(healthMetric = healthMetrics[index])
            }
            
            item {
                Text(
                    "Weekly Summary",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
            
            item {
                WeeklySummaryCard()
            }
            
            item {
                Text(
                    "Health Insights",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
            
            item {
                HealthInsightCard(
                    insight = "Great job staying active this week!",
                    details = "You've averaged 7,650 steps per day, which is above the recommended 7,000 for maintaining good health."
                )
            }
            
            item {
                HealthInsightCard(
                    insight = "Consider increasing protein intake",
                    details = "Based on your activity level, you might benefit from 10-15g more protein daily to support muscle recovery."
                )
            }
        }
    }
}

@Composable
fun HealthMetricCard(
    healthMetric: HealthMetric
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        healthMetric.icon,
                        contentDescription = healthMetric.title,
                        modifier = Modifier.size(24.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.size(12.dp))
                    Column {
                        Text(
                            healthMetric.title,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Medium
                        )
                        healthMetric.target?.let { target ->
                            Text(
                                target,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
                
                Row(
                    verticalAlignment = Alignment.Baseline
                ) {
                    Text(
                        healthMetric.value,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        " ${healthMetric.unit}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
            
            if (healthMetric.progress > 0) {
                Spacer(modifier = Modifier.height(12.dp))
                LinearProgressIndicator(
                    progress = healthMetric.progress,
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    "${(healthMetric.progress * 100).toInt()}% of daily goal",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
fun WeeklySummaryCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                "This Week's Achievements",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onTertiaryContainer
            )
            Spacer(modifier = Modifier.height(12.dp))
            
            WeeklyStat(
                label = "Active Days",
                value = "6 out of 7"
            )
            WeeklyStat(
                label = "Average Calories",
                value = "1,850 per day"
            )
            WeeklyStat(
                label = "Nutrition Score",
                value = "8.2 / 10"
            )
        }
    }
}

@Composable
fun WeeklyStat(
    label: String,
    value: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            label,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onTertiaryContainer
        )
        Text(
            value,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onTertiaryContainer
        )
    }
}

@Composable
fun HealthInsightCard(
    insight: String,
    details: String
) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                insight,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                details,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}