package com.midoriai.leaflogic.di

import android.content.Context
import androidx.room.Room
import com.midoriai.leaflogic.data.local.database.LeafLogicDatabase
import com.midoriai.leaflogic.data.local.dao.FoodEntryDao
import com.midoriai.leaflogic.data.local.dao.HealthMetricsDao
import com.midoriai.leaflogic.data.local.dao.ChatMessageDao
import com.midoriai.leaflogic.data.local.dao.UserGoalsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt module for providing database dependencies
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    
    @Provides
    @Singleton
    fun provideLeafLogicDatabase(
        @ApplicationContext context: Context
    ): LeafLogicDatabase {
        return Room.databaseBuilder(
            context,
            LeafLogicDatabase::class.java,
            LeafLogicDatabase.DATABASE_NAME
        )
        .fallbackToDestructiveMigration()
        .build()
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