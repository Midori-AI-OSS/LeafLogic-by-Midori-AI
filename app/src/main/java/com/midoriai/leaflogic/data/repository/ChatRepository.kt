package com.midoriai.leaflogic.data.repository

import com.midoriai.leaflogic.data.api.ChatRequest
import com.midoriai.leaflogic.data.api.ChatResponse
import com.midoriai.leaflogic.data.api.NutritionApiService
import com.midoriai.leaflogic.data.api.NutritionContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository for handling chat functionality with the LLM nutrition assistant
 */
@Singleton
class ChatRepository @Inject constructor(
    private val apiService: NutritionApiService
) {
    
    fun sendMessage(
        message: String,
        userId: String,
        context: NutritionContext? = null
    ): Flow<Result<ChatResponse>> = flow {
        try {
            val request = ChatRequest(
                message = message,
                userId = userId,
                context = context
            )
            
            // TODO: Replace with actual API key management
            val response = apiService.sendChatMessage(
                authorization = "Bearer YOUR_API_KEY_HERE",
                request = request
            )
            
            if (response.isSuccessful) {
                response.body()?.let { chatResponse ->
                    emit(Result.success(chatResponse))
                } ?: emit(Result.failure(Exception("Empty response body")))
            } else {
                emit(Result.failure(Exception("API call failed: ${response.code()}")))
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
    
    /**
     * Mock implementation for demo purposes
     * This simulates LLM responses for different types of nutrition questions
     */
    fun getMockResponse(message: String): ChatResponse {
        val response = when {
            message.lowercase().contains("tired") || message.lowercase().contains("energy") -> {
                "Fatigue can often be related to nutrition! Based on your recent meals, consider adding more iron-rich foods like spinach, lean meat, and legumes. Also, make sure you're staying hydrated - aim for 8-10 glasses of water daily."
            }
            message.lowercase().contains("weight") || message.lowercase().contains("lose") -> {
                "For healthy weight management, focus on creating a moderate caloric deficit through balanced nutrition and regular activity. Aim for 1-2 pounds per week, prioritizing whole foods, lean proteins, and plenty of vegetables."
            }
            message.lowercase().contains("protein") -> {
                "Based on your activity level, aim for about 0.8-1.2g of protein per kg of body weight daily. Great sources include lean meats, fish, eggs, beans, and Greek yogurt. Spread protein throughout the day for optimal absorption."
            }
            message.lowercase().contains("water") || message.lowercase().contains("hydration") -> {
                "Great question! For your body weight and activity level, aim for 8-10 glasses (64-80 oz) of water daily. Start your day with a glass of water and keep a bottle nearby as a reminder."
            }
            message.lowercase().contains("breakfast") -> {
                "A balanced breakfast should include protein, healthy fats, and complex carbs. Try oatmeal with berries and nuts, Greek yogurt with granola, or eggs with whole grain toast and avocado."
            }
            else -> {
                "I'm here to help with all your nutrition questions! You can ask me about meal planning, healthy recipes, nutrient information, or how to reach your health goals. What specific area would you like to focus on?"
            }
        }
        
        val suggestions = listOf(
            "Tell me about meal planning",
            "What are healthy snack options?",
            "How can I increase my vegetable intake?",
            "Suggest a post-workout meal"
        )
        
        return ChatResponse(
            response = response,
            confidence = 0.85f,
            suggestions = suggestions.shuffled().take(2)
        )
    }
}