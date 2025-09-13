package com.midoriai.leaflogic.data.security

import android.content.Context
import androidx.fragment.app.FragmentActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Central security manager that coordinates all security features
 * Manages encryption keys, biometric authentication, and secure data storage
 */
@Singleton
class SecurityManager @Inject constructor(
    private val context: Context,
    private val biometricAuthenticator: BiometricAuthenticator,
    private val encryptedDataManager: EncryptedDataManager,
    private val photoBasedKeyGenerator: PhotoBasedKeyGenerator
) {
    
    private var currentDeviceKey: String? = null
    private var isAuthenticated: Boolean = false
    
    /**
     * Initialize security system with optional photo enhancement
     */
    suspend fun initializeSecurity(
        photoInputStream: InputStream? = null,
        userSalt: String = ""
    ): SecurityInitResult = withContext(Dispatchers.IO) {
        try {
            // Generate base device fingerprint
            val deviceFingerprint = photoBasedKeyGenerator.generateDeviceFingerprint()
            
            // Extract photo metadata if photo is provided
            val photoMetadata = photoInputStream?.let { stream ->
                photoBasedKeyGenerator.extractPhotoMetadata(stream)
            }
            
            // Generate enhanced encryption key
            val enhancedKey = photoBasedKeyGenerator.generateEnhancedKey(
                deviceFingerprint,
                photoMetadata,
                userSalt
            )
            
            // Validate key strength
            if (!photoBasedKeyGenerator.validateKeyStrength(enhancedKey)) {
                return@withContext SecurityInitResult.WeakKey
            }
            
            currentDeviceKey = enhancedKey
            
            // Store security initialization flag
            encryptedDataManager.setSecuritySetting("initialized", true)
            encryptedDataManager.setSecuritySetting("photo_enhanced", photoMetadata != null)
            
            SecurityInitResult.Success(enhancedKey)
            
        } catch (e: Exception) {
            SecurityInitResult.Error(e.message ?: "Unknown security initialization error")
        }
    }
    
    /**
     * Authenticate user with biometrics if available
     */
    suspend fun authenticateUser(
        activity: FragmentActivity,
        requireBiometric: Boolean = true
    ): AuthenticationResult {
        return if (requireBiometric && biometricAuthenticator.isBiometricAvailable(context)) {
            when (val result = biometricAuthenticator.authenticate(activity)) {
                is BiometricResult.Success -> {
                    isAuthenticated = true
                    AuthenticationResult.Success
                }
                is BiometricResult.Failed -> AuthenticationResult.Failed
                is BiometricResult.Error -> AuthenticationResult.Error(result.message)
            }
        } else {
            // Fallback to device key validation if biometrics not available
            if (currentDeviceKey != null) {
                isAuthenticated = true
                AuthenticationResult.Success
            } else {
                AuthenticationResult.Error("Security not initialized")
            }
        }
    }
    
    /**
     * Store sensitive user data with encryption
     */
    suspend fun storeSecureData(
        userId: String,
        dataType: DataType,
        data: String,
        timestamp: Long = System.currentTimeMillis()
    ): Boolean {
        if (!isAuthenticated || currentDeviceKey == null) {
            return false
        }
        
        return when (dataType) {
            DataType.HEALTH_METRICS -> {
                val storageKey = photoBasedKeyGenerator.generateStorageKey(
                    currentDeviceKey!!,
                    dataType,
                    userId
                )
                encryptedDataManager.storeHealthMetric(userId, storageKey, data, timestamp)
            }
            DataType.FOOD_ENTRIES -> {
                encryptedDataManager.storeFoodEntry(userId, data, timestamp)
            }
            DataType.USER_PROFILE -> {
                val profileData = mapOf("data" to data)
                encryptedDataManager.storeUserProfile(userId, profileData)
            }
            else -> {
                // For other data types, use generic storage
                encryptedDataManager.storeHealthMetric(userId, dataType.name, data, timestamp)
            }
        }
    }
    
    /**
     * Retrieve secure data with authentication check
     */
    suspend fun getSecureData(
        userId: String,
        dataType: DataType
    ): Map<Long, String> {
        if (!isAuthenticated || currentDeviceKey == null) {
            return emptyMap()
        }
        
        return when (dataType) {
            DataType.HEALTH_METRICS -> {
                val storageKey = photoBasedKeyGenerator.generateStorageKey(
                    currentDeviceKey!!,
                    dataType,
                    userId
                )
                encryptedDataManager.getHealthMetrics(userId, storageKey)
            }
            DataType.FOOD_ENTRIES -> {
                encryptedDataManager.getFoodEntries(userId)
            }
            DataType.USER_PROFILE -> {
                val profileData = encryptedDataManager.getUserProfile(userId)
                profileData.mapKeys { System.currentTimeMillis() } // Convert to timestamp map
            }
            else -> {
                encryptedDataManager.getHealthMetrics(userId, dataType.name)
            }
        }
    }
    
    /**
     * Check if security features are properly configured
     */
    suspend fun getSecurityStatus(): SecurityStatus = withContext(Dispatchers.IO) {
        val isInitialized = encryptedDataManager.getSecuritySetting("initialized", false)
        val isPhotoEnhanced = encryptedDataManager.getSecuritySetting("photo_enhanced", false)
        val hasBiometric = biometricAuthenticator.isBiometricAvailable(context)
        
        SecurityStatus(
            isInitialized = isInitialized,
            isPhotoEnhanced = isPhotoEnhanced,
            hasBiometricAuth = hasBiometric,
            isAuthenticated = isAuthenticated,
            hasValidKey = currentDeviceKey != null && 
                          photoBasedKeyGenerator.validateKeyStrength(currentDeviceKey!!)
        )
    }
    
    /**
     * Clear all security data and reset authentication
     */
    suspend fun clearSecurityData(userId: String): Boolean {
        return try {
            encryptedDataManager.clearUserData(userId)
            encryptedDataManager.setSecuritySetting("initialized", false)
            encryptedDataManager.setSecuritySetting("photo_enhanced", false)
            
            currentDeviceKey = null
            isAuthenticated = false
            
            true
        } catch (e: Exception) {
            false
        }
    }
    
    /**
     * Generate a new encryption key with updated photo
     */
    suspend fun updateEncryptionKey(
        photoInputStream: InputStream,
        userSalt: String = ""
    ): Boolean {
        return try {
            val result = initializeSecurity(photoInputStream, userSalt)
            result is SecurityInitResult.Success
        } catch (e: Exception) {
            false
        }
    }
    
    /**
     * Get current device fingerprint (non-sensitive info)
     */
    suspend fun getDeviceFingerprint(): String {
        return photoBasedKeyGenerator.generateDeviceFingerprint()
    }
}

/**
 * Result of security system initialization
 */
sealed class SecurityInitResult {
    data class Success(val key: String) : SecurityInitResult()
    object WeakKey : SecurityInitResult()
    data class Error(val message: String) : SecurityInitResult()
}

/**
 * Result of user authentication
 */
sealed class AuthenticationResult {
    object Success : AuthenticationResult()
    object Failed : AuthenticationResult()
    data class Error(val message: String) : AuthenticationResult()
}

/**
 * Current security system status
 */
data class SecurityStatus(
    val isInitialized: Boolean,
    val isPhotoEnhanced: Boolean,
    val hasBiometricAuth: Boolean,
    val isAuthenticated: Boolean,
    val hasValidKey: Boolean
)