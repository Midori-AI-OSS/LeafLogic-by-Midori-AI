package com.midoriai.leaflogic.di

import android.content.Context
import com.midoriai.leaflogic.data.local.dao.ChatMessageDao
import com.midoriai.leaflogic.data.local.dao.FoodEntryDao
import com.midoriai.leaflogic.data.local.dao.HealthMetricsDao
import com.midoriai.leaflogic.data.local.dao.UserGoalsDao
import com.midoriai.leaflogic.data.local.database.LeafLogicDatabase
import com.midoriai.leaflogic.data.security.SecurityManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt module for providing database dependencies with encryption support
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    
    @Provides
    @Singleton
    fun provideLeafLogicDatabase(
        @ApplicationContext context: Context,
        securityManager: SecurityManager
    ): LeafLogicDatabase {
        // For now, create a standard database
        // In production, use encrypted database with key from SecurityManager
        return LeafLogicDatabase.createStandardDatabase(context)
        
        // TODO: Implement encrypted database creation
        // val deviceFingerprint = runBlocking { securityManager.getDeviceFingerprint() }
        // return LeafLogicDatabase.createEncryptedDatabase(context, deviceFingerprint)
    }
    
    @Provides
    fun provideFoodEntryDao(database: LeafLogicDatabase): FoodEntryDao {
        return database.foodEntryDao()
    }
    
    @Provides
    fun provideHealthMetricsDao(database: LeafLogicDatabase): HealthMetricsDao {
        return database.healthMetricsDao()
    }
    
    @Provides
    fun provideChatMessageDao(database: LeafLogicDatabase): ChatMessageDao {
        return database.chatMessageDao()
    }
    
    @Provides
    fun provideUserGoalsDao(database: LeafLogicDatabase): UserGoalsDao {
        return database.userGoalsDao()
    }
}