package com.midoriai.leaflogic.di

import android.content.Context
import com.midoriai.leaflogic.data.security.BiometricAuthenticator
import com.midoriai.leaflogic.data.security.EncryptedDataManager
import com.midoriai.leaflogic.data.security.PhotoBasedKeyGenerator
import com.midoriai.leaflogic.data.security.SecurityManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt module for providing security-related dependencies
 */
@Module
@InstallIn(SingletonComponent::class)
object SecurityModule {
    
    @Provides
    @Singleton
    fun provideBiometricAuthenticator(): BiometricAuthenticator {
        return BiometricAuthenticator()
    }
    
    @Provides
    @Singleton
    fun provideEncryptedDataManager(
        @ApplicationContext context: Context
    ): EncryptedDataManager {
        return EncryptedDataManager(context)
    }
    
    @Provides
    @Singleton
    fun providePhotoBasedKeyGenerator(
        @ApplicationContext context: Context
    ): PhotoBasedKeyGenerator {
        return PhotoBasedKeyGenerator(context)
    }
    
    @Provides
    @Singleton
    fun provideSecurityManager(
        @ApplicationContext context: Context,
        biometricAuthenticator: BiometricAuthenticator,
        encryptedDataManager: EncryptedDataManager,
        photoBasedKeyGenerator: PhotoBasedKeyGenerator
    ): SecurityManager {
        return SecurityManager(
            context,
            biometricAuthenticator,
            encryptedDataManager,
            photoBasedKeyGenerator
        )
    }
}