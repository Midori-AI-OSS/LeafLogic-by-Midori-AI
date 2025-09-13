package com.midoriai.leaflogic.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.midoriai.leaflogic.data.local.dao.ChatMessageDao
import com.midoriai.leaflogic.data.local.dao.FoodEntryDao
import com.midoriai.leaflogic.data.local.dao.HealthMetricsDao
import com.midoriai.leaflogic.data.local.dao.UserGoalsDao
import com.midoriai.leaflogic.data.local.entity.ChatMessageEntity
import com.midoriai.leaflogic.data.local.entity.FoodEntryEntity
import com.midoriai.leaflogic.data.local.entity.HealthMetricsEntity
import com.midoriai.leaflogic.data.local.entity.UserGoalsEntity
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory

/**
 * Room database for local data storage with encryption support
 * Uses SQLCipher for database encryption to protect sensitive health data
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
        
        /**
         * Create an encrypted database instance
         * Uses a passphrase derived from device characteristics for security
         */
        fun createEncryptedDatabase(
            context: Context, 
            passphrase: String
        ): LeafLogicDatabase {
            // Create SQLCipher support factory with the passphrase
            val supportFactory = SupportFactory(SQLiteDatabase.getBytes(passphrase.toCharArray()))
            
            return Room.databaseBuilder(
                context,
                LeafLogicDatabase::class.java,
                DATABASE_NAME
            )
            .openHelperFactory(supportFactory)
            .fallbackToDestructiveMigration() // For development - remove in production
            .build()
        }
        
        /**
         * Create a standard (non-encrypted) database instance for testing or fallback
         */
        fun createStandardDatabase(context: Context): LeafLogicDatabase {
            return Room.databaseBuilder(
                context,
                LeafLogicDatabase::class.java,
                "${DATABASE_NAME}_standard"
            )
            .fallbackToDestructiveMigration()
            .build()
        }
    }
}