package com.lugopc.pro.data.local.dao

import androidx.room.*
import com.lugopc.pro.domain.models.AttendanceSchedule
import kotlinx.coroutines.flow.Flow

@Dao
interface AttendanceScheduleDao {
    
    @Query("SELECT * FROM attendance_schedule ORDER BY dayOfWeek")
    fun getAllSchedules(): Flow<List<AttendanceSchedule>>
    
    @Query("SELECT * FROM attendance_schedule WHERE dayOfWeek = :dayOfWeek")
    suspend fun getScheduleByDay(dayOfWeek: Int): AttendanceSchedule?
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSchedule(schedule: AttendanceSchedule): Long
    
    @Update
    suspend fun updateSchedule(schedule: AttendanceSchedule)
    
    @Delete
    suspend fun deleteSchedule(schedule: AttendanceSchedule)
}
