package com.midoriai.leaflogic.data.security

import android.content.Context
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.resume

/**
 * Handles biometric authentication for secure data access
 */
@Singleton
class BiometricAuthenticator @Inject constructor() {
    
    /**
     * Check if biometric authentication is available on the device
     */
    fun isBiometricAvailable(context: Context): Boolean {
        val biometricManager = BiometricManager.from(context)
        return when (biometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_WEAK)) {
            BiometricManager.BIOMETRIC_SUCCESS -> true
            else -> false
        }
    }
    
    /**
     * Authenticate user with biometrics
     */
    suspend fun authenticate(
        activity: FragmentActivity,
        title: String = "Biometric Authentication",
        subtitle: String = "Use your fingerprint or face to access your health data",
        negativeButtonText: String = "Cancel"
    ): BiometricResult = suspendCancellableCoroutine { continuation ->
        
        val executor = ContextCompat.getMainExecutor(activity)
        val biometricPrompt = BiometricPrompt(activity, executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    if (continuation.isActive) {
                        continuation.resume(BiometricResult.Error(errString.toString()))
                    }
                }
                
                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    if (continuation.isActive) {
                        continuation.resume(BiometricResult.Success)
                    }
                }
                
                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    if (continuation.isActive) {
                        continuation.resume(BiometricResult.Failed)
                    }
                }
            })
        
        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle(title)
            .setSubtitle(subtitle)
            .setNegativeButtonText(negativeButtonText)
            .setAllowedAuthenticators(BiometricManager.Authenticators.BIOMETRIC_WEAK)
            .build()
        
        biometricPrompt.authenticate(promptInfo)
        
        continuation.invokeOnCancellation {
            biometricPrompt.cancelAuthentication()
        }
    }
}

/**
 * Represents the result of biometric authentication
 */
sealed class BiometricResult {
    object Success : BiometricResult()
    object Failed : BiometricResult()
    data class Error(val message: String) : BiometricResult()
}