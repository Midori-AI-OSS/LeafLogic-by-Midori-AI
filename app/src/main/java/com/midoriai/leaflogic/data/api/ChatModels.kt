package com.midoriai.leaflogic.data.api

import kotlinx.serialization.Serializable

/**
 * Data models for cloud LLM API communication
 */

@Serializable
data class ChatRequest(
    val message: String,
    val userId: String,
    val context: NutritionContext? = null
)

@Serializable
data class ChatResponse(
    val response: String,
    val confidence: Float,
    val suggestions: List<String> = emptyList(),
    val timestamp: Long = System.currentTimeMillis()
)

@Serializable
data class NutritionContext(
    val recentMeals: List<FoodItem> = emptyList(),
    val healthMetrics: HealthMetrics? = null,
    val goals: UserGoals? = null,
    val preferences: UserPreferences? = null
)

@Serializable
data class FoodItem(
    val name: String,
    val calories: Int,
    val protein: Float = 0f,
    val carbs: Float = 0f,
    val fat: Float = 0f,
    val fiber: Float = 0f,
    val timestamp: Long
)

@Serializable
data class HealthMetrics(
    val weight: Float? = null,
    val height: Float? = null,
    val age: Int? = null,
    val activityLevel: String = "moderate",
    val dailySteps: Int = 0
)

@Serializable
data class UserGoals(
    val targetCalories: Int = 2000,
    val targetWeight: Float? = null,
    val targetSteps: Int = 10000,
    val dietaryGoals: List<String> = emptyList()
)

@Serializable
data class UserPreferences(
    val dietaryRestrictions: List<String> = emptyList(),
    val allergies: List<String> = emptyList(),
    val preferredCuisines: List<String> = emptyList(),
    val dislikedFoods: List<String> = emptyList()
)