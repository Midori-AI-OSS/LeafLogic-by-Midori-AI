package com.midoriai.leaflogic.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.midoriai.leaflogic.data.local.entity.FoodEntryEntity
import com.midoriai.leaflogic.data.local.entity.HealthMetricsEntity
import com.midoriai.leaflogic.data.local.entity.ChatMessageEntity
import com.midoriai.leaflogic.data.local.entity.UserGoalsEntity
import kotlinx.coroutines.flow.Flow

/**
 * Room Data Access Objects (DAOs)
 */

@Dao
interface FoodEntryDao {
    @Query("SELECT * FROM food_entries ORDER BY timestamp DESC")
    fun getAllFoodEntries(): Flow<List<FoodEntryEntity>>
    
    @Query("SELECT * FROM food_entries WHERE date(timestamp/1000, 'unixepoch') = date('now') ORDER BY timestamp DESC")
    fun getTodaysFoodEntries(): Flow<List<FoodEntryEntity>>
    
    @Query("SELECT SUM(calories) FROM food_entries WHERE date(timestamp/1000, 'unixepoch') = date('now')")
    fun getTodaysCalories(): Flow<Int?>
    
    @Insert
    suspend fun insertFoodEntry(foodEntry: FoodEntryEntity)
    
    @Query("DELETE FROM food_entries WHERE id = :id")
    suspend fun deleteFoodEntry(id: Long)
}

@Dao
interface HealthMetricsDao {
    @Query("SELECT * FROM health_metrics ORDER BY timestamp DESC LIMIT 7")
    fun getRecentHealthMetrics(): Flow<List<HealthMetricsEntity>>
    
    @Query("SELECT * FROM health_metrics WHERE date = :date LIMIT 1")
    fun getHealthMetricsForDate(date: String): Flow<HealthMetricsEntity?>
    
    @Insert
    suspend fun insertHealthMetrics(metrics: HealthMetricsEntity)
    
    @Update
    suspend fun updateHealthMetrics(metrics: HealthMetricsEntity)
}

@Dao
interface ChatMessageDao {
    @Query("SELECT * FROM chat_messages ORDER BY timestamp ASC")
    fun getAllChatMessages(): Flow<List<ChatMessageEntity>>
    
    @Query("SELECT * FROM chat_messages WHERE sessionId = :sessionId ORDER BY timestamp ASC")
    fun getChatMessagesForSession(sessionId: String): Flow<List<ChatMessageEntity>>
    
    @Insert
    suspend fun insertChatMessage(message: ChatMessageEntity)
    
    @Query("DELETE FROM chat_messages")
    suspend fun clearAllMessages()
}

@Dao
interface UserGoalsDao {
    @Query("SELECT * FROM user_goals WHERE id = 1 LIMIT 1")
    fun getUserGoals(): Flow<UserGoalsEntity?>
    
    @Insert
    suspend fun insertUserGoals(goals: UserGoalsEntity)
    
    @Update
    suspend fun updateUserGoals(goals: UserGoalsEntity)
}