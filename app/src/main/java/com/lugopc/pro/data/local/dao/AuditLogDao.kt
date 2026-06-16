package com.lugopc.pro.data.local.dao

import androidx.room.*
import com.lugopc.pro.domain.models.AuditLog
import kotlinx.coroutines.flow.Flow

@Dao
interface AuditLogDao {
    
    @Query("SELECT * FROM audit_logs ORDER BY timestamp DESC LIMIT :limit")
    fun getRecentLogs(limit: Int = 100): Flow<List<AuditLog>>
    
    @Query("SELECT * FROM audit_logs WHERE userId = :userId ORDER BY timestamp DESC")
    fun getLogsByUser(userId: String): Flow<List<AuditLog>>
    
    @Query("SELECT * FROM audit_logs WHERE entityId = :entityId ORDER BY timestamp DESC")
    fun getLogsByEntity(entityId: String): Flow<List<AuditLog>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLog(log: AuditLog): Long
    
    @Query("DELETE FROM audit_logs WHERE timestamp < datetime('now', '-90 days')")
    suspend fun deleteOldLogs()
}
