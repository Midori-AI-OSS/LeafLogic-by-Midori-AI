package com.midoriai.leaflogic.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Room database entities for local data storage
 */

@Entity(tableName = "food_entries")
data class FoodEntryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val calories: Int,
    val protein: Float = 0f,
    val carbs: Float = 0f,
    val fat: Float = 0f,
    val fiber: Float = 0f,
    val meal: String, // breakfast, lunch, dinner, snack
    val timestamp: Long = System.currentTimeMillis()
)

@Entity(tableName = "health_metrics")
data class HealthMetricsEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val weight: Float? = null,
    val steps: Int = 0,
    val waterIntake: Float = 0f, // in liters
    val sleepHours: Float = 0f,
    val date: String, // YYYY-MM-DD format
    val timestamp: Long = System.currentTimeMillis()
)

@Entity(tableName = "chat_messages")
data class ChatMessageEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val message: String,
    val isFromUser: Boolean,
    val timestamp: Long = System.currentTimeMillis(),
    val sessionId: String = ""
)

@Entity(tableName = "user_goals")
data class UserGoalsEntity(
    @PrimaryKey
    val id: Long = 1, // Single row table
    val targetCalories: Int = 2000,
    val targetWeight: Float? = null,
    val targetSteps: Int = 10000,
    val targetWater: Float = 2.0f, // in liters
    val lastUpdated: Long = System.currentTimeMillis()
)