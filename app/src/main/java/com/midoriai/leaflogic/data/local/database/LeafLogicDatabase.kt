package com.midoriai.leaflogic.data.local.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import com.midoriai.leaflogic.data.local.entity.FoodEntryEntity
import com.midoriai.leaflogic.data.local.entity.HealthMetricsEntity
import com.midoriai.leaflogic.data.local.entity.ChatMessageEntity
import com.midoriai.leaflogic.data.local.entity.UserGoalsEntity
import com.midoriai.leaflogic.data.local.dao.FoodEntryDao
import com.midoriai.leaflogic.data.local.dao.HealthMetricsDao
import com.midoriai.leaflogic.data.local.dao.ChatMessageDao
import com.midoriai.leaflogic.data.local.dao.UserGoalsDao

/**
 * Room database for local data storage
 */
@Database(
    entities = [
        FoodEntryEntity::class,
        HealthMetricsEntity::class,
        ChatMessageEntity::class,
        UserGoalsEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class LeafLogicDatabase : RoomDatabase() {
    
    abstract fun foodEntryDao(): FoodEntryDao
    abstract fun healthMetricsDao(): HealthMetricsDao
    abstract fun chatMessageDao(): ChatMessageDao
    abstract fun userGoalsDao(): UserGoalsDao
    
    companion object {
        const val DATABASE_NAME = "leaflogic_database"
    }
}