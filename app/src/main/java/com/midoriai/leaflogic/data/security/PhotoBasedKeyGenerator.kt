package com.midoriai.leaflogic.data.security

import android.content.Context
import android.os.Build
import android.provider.Settings
import androidx.exifinterface.media.ExifInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.security.MessageDigest
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Generates encryption keys based on device characteristics and photo metadata
 * This provides additional security layer using device-specific information
 */
@Singleton
class PhotoBasedKeyGenerator @Inject constructor(
    private val context: Context
) {
    
    /**
     * Generate a unique device fingerprint for encryption
     * This combines multiple device characteristics to create a unique identifier
     */
    suspend fun generateDeviceFingerprint(): String = withContext(Dispatchers.IO) {
        val deviceInfo = StringBuilder()
        
        try {
            // Device hardware information
            deviceInfo.append(Build.MANUFACTURER)
            deviceInfo.append(Build.MODEL)
            deviceInfo.append(Build.DEVICE)
            deviceInfo.append(Build.HARDWARE)
            
            // Android system information
            deviceInfo.append(Build.VERSION.SDK_INT)
            deviceInfo.append(Build.VERSION.INCREMENTAL)
            
            // Secure settings (if available)
            try {
                val androidId = Settings.Secure.getString(
                    context.contentResolver,
                    Settings.Secure.ANDROID_ID
                )
                if (!androidId.isNullOrEmpty()) {
                    deviceInfo.append(androidId)
                }
            } catch (e: Exception) {
                // Fallback if ANDROID_ID is not available
                deviceInfo.append("fallback_device_id")
            }
            
            // App-specific information
            deviceInfo.append(context.packageName)
            
        } catch (e: Exception) {
            // Fallback device fingerprint
            deviceInfo.append("default_device_fingerprint")
        }
        
        // Create SHA-256 hash of the device information
        hashString(deviceInfo.toString())
    }
    
    /**
     * Extract metadata from photo to enhance encryption key
     * This analyzes photo EXIF data and characteristics
     */
    suspend fun extractPhotoMetadata(photoInputStream: InputStream): PhotoMetadata = withContext(Dispatchers.IO) {
        try {
            val exif = ExifInterface(photoInputStream)
            
            PhotoMetadata(
                dateTime = exif.getAttribute(ExifInterface.TAG_DATETIME) ?: "",
                make = exif.getAttribute(ExifInterface.TAG_MAKE) ?: "",
                model = exif.getAttribute(ExifInterface.TAG_MODEL) ?: "",
                orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, 0),
                imageWidth = exif.getAttributeInt(ExifInterface.TAG_IMAGE_WIDTH, 0),
                imageHeight = exif.getAttributeInt(ExifInterface.TAG_IMAGE_LENGTH, 0),
                gpsLatitude = exif.getAttribute(ExifInterface.TAG_GPS_LATITUDE) ?: "",
                gpsLongitude = exif.getAttribute(ExifInterface.TAG_GPS_LONGITUDE) ?: "",
                software = exif.getAttribute(ExifInterface.TAG_SOFTWARE) ?: ""
            )
        } catch (e: Exception) {
            // Return empty metadata if extraction fails
            PhotoMetadata()
        }
    }
    
    /**
     * Generate an enhanced encryption key using device fingerprint and photo metadata
     */
    suspend fun generateEnhancedKey(
        deviceFingerprint: String,
        photoMetadata: PhotoMetadata? = null,
        userSalt: String = ""
    ): String = withContext(Dispatchers.IO) {
        val keyBuilder = StringBuilder()
        
        // Base device fingerprint
        keyBuilder.append(deviceFingerprint)
        
        // Add photo metadata if available
        photoMetadata?.let { metadata ->
            keyBuilder.append(metadata.make)
            keyBuilder.append(metadata.model)
            keyBuilder.append(metadata.dateTime)
            keyBuilder.append(metadata.orientation)
            keyBuilder.append(metadata.imageWidth)
            keyBuilder.append(metadata.imageHeight)
            keyBuilder.append(metadata.software)
            // Note: GPS coordinates are not included for privacy
        }
        
        // Add user-specific salt
        if (userSalt.isNotEmpty()) {
            keyBuilder.append(userSalt)
        }
        
        // Add app-specific constant
        keyBuilder.append("LeafLogic_Security_2024")
        
        // Create SHA-256 hash of the combined data
        hashString(keyBuilder.toString())
    }
    
    /**
     * Generate a storage key for a specific data type
     */
    suspend fun generateStorageKey(
        baseKey: String,
        dataType: DataType,
        userId: String
    ): String = withContext(Dispatchers.IO) {
        val storageKeyBuilder = StringBuilder()
        storageKeyBuilder.append(baseKey)
        storageKeyBuilder.append(dataType.name)
        storageKeyBuilder.append(userId)
        
        hashString(storageKeyBuilder.toString()).take(32) // Limit to 32 characters for key
    }
    
    /**
     * Create SHA-256 hash of input string
     */
    private fun hashString(input: String): String {
        val digest = MessageDigest.getInstance("SHA-256")
        val hashBytes = digest.digest(input.toByteArray())
        return hashBytes.joinToString("") { "%02x".format(it) }
    }
    
    /**
     * Validate that a key meets security requirements
     */
    fun validateKeyStrength(key: String): Boolean {
        return key.length >= 32 && 
               key.any { it.isDigit() } &&
               key.any { it.isLetter() }
    }
}

/**
 * Data class to hold photo metadata extracted from EXIF data
 */
data class PhotoMetadata(
    val dateTime: String = "",
    val make: String = "",
    val model: String = "",
    val orientation: Int = 0,
    val imageWidth: Int = 0,
    val imageHeight: Int = 0,
    val gpsLatitude: String = "",
    val gpsLongitude: String = "",
    val software: String = ""
)

/**
 * Enum for different types of data that can be encrypted
 */
enum class DataType {
    HEALTH_METRICS,
    FOOD_ENTRIES,
    USER_PROFILE,
    CHAT_MESSAGES,
    GOALS_AND_PREFERENCES
}