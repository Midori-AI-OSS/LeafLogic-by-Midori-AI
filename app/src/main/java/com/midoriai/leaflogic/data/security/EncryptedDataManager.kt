package com.midoriai.leaflogic.data.security

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Manages encrypted storage for sensitive user data
 * Uses Android Jetpack Security library for encryption
 */
@Singleton
class EncryptedDataManager @Inject constructor(
    private val context: Context
) {
    
    private val masterKey: MasterKey by lazy {
        MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()
    }
    
    private val encryptedPrefs: SharedPreferences by lazy {
        EncryptedSharedPreferences.create(
            context,
            "leaflogic_secure_prefs",
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }
    
    /**
     * Store encrypted user profile data
     */
    suspend fun storeUserProfile(
        userId: String,
        profileData: Map<String, String>
    ): Boolean = withContext(Dispatchers.IO) {
        try {
            val editor = encryptedPrefs.edit()
            profileData.forEach { (key, value) ->
                editor.putString("profile_${userId}_$key", value)
            }
            editor.apply()
            true
        } catch (e: Exception) {
            false
        }
    }
    
    /**
     * Retrieve encrypted user profile data
     */
    suspend fun getUserProfile(userId: String): Map<String, String> = withContext(Dispatchers.IO) {
        try {
            val profileData = mutableMapOf<String, String>()
            val allPrefs = encryptedPrefs.all
            val prefix = "profile_${userId}_"
            
            allPrefs.forEach { (key, value) ->
                if (key.startsWith(prefix) && value is String) {
                    val actualKey = key.removePrefix(prefix)
                    profileData[actualKey] = value
                }
            }
            profileData
        } catch (e: Exception) {
            emptyMap()
        }
    }
    
    /**
     * Store encrypted health metrics
     */
    suspend fun storeHealthMetric(
        userId: String,
        metricType: String,
        value: String,
        timestamp: Long
    ): Boolean = withContext(Dispatchers.IO) {
        try {
            val key = "health_${userId}_${metricType}_$timestamp"
            encryptedPrefs.edit()
                .putString(key, value)
                .apply()
            true
        } catch (e: Exception) {
            false
        }
    }
    
    /**
     * Retrieve encrypted health metrics for a user
     */
    suspend fun getHealthMetrics(
        userId: String,
        metricType: String? = null
    ): Map<Long, String> = withContext(Dispatchers.IO) {
        try {
            val metrics = mutableMapOf<Long, String>()
            val allPrefs = encryptedPrefs.all
            val prefix = if (metricType != null) {
                "health_${userId}_${metricType}_"
            } else {
                "health_${userId}_"
            }
            
            allPrefs.forEach { (key, value) ->
                if (key.startsWith(prefix) && value is String) {
                    try {
                        val timestampStr = key.removePrefix(prefix).split("_").last()
                        val timestamp = timestampStr.toLong()
                        metrics[timestamp] = value
                    } catch (e: NumberFormatException) {
                        // Ignore invalid timestamp
                    }
                }
            }
            metrics
        } catch (e: Exception) {
            emptyMap()
        }
    }
    
    /**
     * Store encrypted food entry data
     */
    suspend fun storeFoodEntry(
        userId: String,
        foodData: String,
        timestamp: Long
    ): Boolean = withContext(Dispatchers.IO) {
        try {
            val key = "food_${userId}_$timestamp"
            encryptedPrefs.edit()
                .putString(key, foodData)
                .apply()
            true
        } catch (e: Exception) {
            false
        }
    }
    
    /**
     * Retrieve encrypted food entries
     */
    suspend fun getFoodEntries(userId: String): Map<Long, String> = withContext(Dispatchers.IO) {
        try {
            val foodEntries = mutableMapOf<Long, String>()
            val allPrefs = encryptedPrefs.all
            val prefix = "food_${userId}_"
            
            allPrefs.forEach { (key, value) ->
                if (key.startsWith(prefix) && value is String) {
                    try {
                        val timestampStr = key.removePrefix(prefix)
                        val timestamp = timestampStr.toLong()
                        foodEntries[timestamp] = value
                    } catch (e: NumberFormatException) {
                        // Ignore invalid timestamp
                    }
                }
            }
            foodEntries
        } catch (e: Exception) {
            emptyMap()
        }
    }
    
    /**
     * Clear all encrypted data for a user
     */
    suspend fun clearUserData(userId: String): Boolean = withContext(Dispatchers.IO) {
        try {
            val editor = encryptedPrefs.edit()
            val allPrefs = encryptedPrefs.all
            val prefixes = listOf("profile_$userId", "health_$userId", "food_$userId")
            
            allPrefs.keys.forEach { key ->
                if (prefixes.any { prefix -> key.startsWith(prefix) }) {
                    editor.remove(key)
                }
            }
            editor.apply()
            true
        } catch (e: Exception) {
            false
        }
    }
    
    /**
     * Store security settings
     */
    suspend fun setSecuritySetting(key: String, value: Boolean): Boolean = withContext(Dispatchers.IO) {
        try {
            encryptedPrefs.edit()
                .putBoolean("security_$key", value)
                .apply()
            true
        } catch (e: Exception) {
            false
        }
    }
    
    /**
     * Get security setting
     */
    suspend fun getSecuritySetting(key: String, defaultValue: Boolean = false): Boolean = withContext(Dispatchers.IO) {
        try {
            encryptedPrefs.getBoolean("security_$key", defaultValue)
        } catch (e: Exception) {
            defaultValue
        }
    }
}