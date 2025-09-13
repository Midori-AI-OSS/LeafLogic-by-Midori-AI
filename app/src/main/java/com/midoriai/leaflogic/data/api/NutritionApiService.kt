package com.midoriai.leaflogic.data.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Header

/**
 * API interface for cloud LLM nutrition assistant
 * This can be configured to work with various LLM providers like OpenAI, Claude, etc.
 */
interface NutritionApiService {
    
    @POST("chat/nutrition")
    suspend fun sendChatMessage(
        @Header("Authorization") authorization: String,
        @Body request: ChatRequest
    ): Response<ChatResponse>
    
    @POST("analyze/food")
    suspend fun analyzeFoodItem(
        @Header("Authorization") authorization: String,
        @Body request: FoodAnalysisRequest
    ): Response<FoodAnalysisResponse>
    
    @POST("recommendations/daily")
    suspend fun getDailyRecommendations(
        @Header("Authorization") authorization: String,
        @Body request: RecommendationRequest
    ): Response<RecommendationResponse>
}

// Additional API models for food analysis and recommendations
@kotlinx.serialization.Serializable
data class FoodAnalysisRequest(
    val foodDescription: String,
    val portionSize: String? = null,
    val mealType: String? = null
)

@kotlinx.serialization.Serializable
data class FoodAnalysisResponse(
    val recognizedFood: FoodItem,
    val nutritionalBreakdown: NutritionalBreakdown,
    val healthScore: Float,
    val recommendations: List<String>
)

@kotlinx.serialization.Serializable
data class NutritionalBreakdown(
    val macronutrients: Macronutrients,
    val micronutrients: Map<String, Float> = emptyMap(),
    val vitamins: Map<String, Float> = emptyMap(),
    val minerals: Map<String, Float> = emptyMap()
)

@kotlinx.serialization.Serializable
data class Macronutrients(
    val protein: Float,
    val carbohydrates: Float,
    val fat: Float,
    val fiber: Float,
    val sugar: Float = 0f,
    val sodium: Float = 0f
)

@kotlinx.serialization.Serializable
data class RecommendationRequest(
    val userId: String,
    val nutritionContext: NutritionContext
)

@kotlinx.serialization.Serializable
data class RecommendationResponse(
    val dailyTips: List<String>,
    val mealSuggestions: List<MealSuggestion>,
    val nutritionalInsights: List<String>,
    val healthWarnings: List<String> = emptyList()
)

@kotlinx.serialization.Serializable
data class MealSuggestion(
    val mealType: String,
    val name: String,
    val description: String,
    val estimatedCalories: Int,
    val ingredients: List<String>,
    val preparationTime: String,
    val healthBenefits: List<String>
)