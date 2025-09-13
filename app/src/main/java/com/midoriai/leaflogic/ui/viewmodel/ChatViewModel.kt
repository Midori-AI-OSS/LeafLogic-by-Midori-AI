package com.midoriai.leaflogic.ui.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.midoriai.leaflogic.data.repository.ChatRepository
import com.midoriai.leaflogic.ui.screens.ChatMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for the LLM Chat screen
 * Manages chat state and communication with the LLM nutrition assistant
 */
@HiltViewModel
class ChatViewModel @Inject constructor(
    private val chatRepository: ChatRepository
) : ViewModel() {
    
    // Chat messages state
    private val _messages = mutableStateListOf<ChatMessage>()
    val messages: List<ChatMessage> = _messages
    
    // Loading state
    private val _isLoading = mutableStateOf(false)
    val isLoading = _isLoading.value
    
    // Error state
    private val _errorMessage = mutableStateOf<String?>(null)
    val errorMessage = _errorMessage.value
    
    init {
        // Initialize with welcome message
        _messages.add(
            ChatMessage(
                text = "Hello! I'm your LLM nutrition assistant. How can I help you improve your eating habits today?",
                isFromUser = false,
                timestamp = "Just now"
            )
        )
    }
    
    /**
     * Send a message to the LLM assistant
     */
    fun sendMessage(messageText: String) {
        if (messageText.isBlank()) return
        
        // Add user message immediately
        _messages.add(
            ChatMessage(
                text = messageText,
                isFromUser = true,
                timestamp = "Just now"
            )
        )
        
        // Show loading state
        _isLoading.value = true
        
        viewModelScope.launch {
            try {
                // For demo purposes, use mock response
                // In production, this would call the actual API through the repository
                val response = chatRepository.getMockResponse(messageText)
                
                // Add LLM response
                _messages.add(
                    ChatMessage(
                        text = response.response,
                        isFromUser = false,
                        timestamp = "Just now"
                    )
                )
                
                // Clear any previous error
                _errorMessage.value = null
                
            } catch (e: Exception) {
                _errorMessage.value = "Failed to get response: ${e.message}"
                
                // Add error message to chat
                _messages.add(
                    ChatMessage(
                        text = "I'm sorry, I'm having trouble connecting right now. Please try again later.",
                        isFromUser = false,
                        timestamp = "Just now"
                    )
                )
            } finally {
                _isLoading.value = false
            }
        }
    }
    
    /**
     * Clear error message
     */
    fun clearError() {
        _errorMessage.value = null
    }
    
    /**
     * Clear all messages
     */
    fun clearChat() {
        _messages.clear()
        // Re-add welcome message
        _messages.add(
            ChatMessage(
                text = "Hello! I'm your LLM nutrition assistant. How can I help you improve your eating habits today?",
                isFromUser = false,
                timestamp = "Just now"
            )
        )
    }
}