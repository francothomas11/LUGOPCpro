package com.lugopc.pro.data.local.dao

import androidx.room.*
import com.lugopc.pro.domain.models.User
import com.lugopc.pro.domain.models.UserRole
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    
    @Query("SELECT * FROM users WHERE id = :id")
    suspend fun getUserById(id: String): User?
    
    @Query("SELECT * FROM users WHERE email = :email")
    suspend fun getUserByEmail(email: String): User?
    
    @Query("SELECT * FROM users WHERE role = :role AND isActive = 1")
    fun getUsersByRole(role: UserRole): Flow<List<User>>
    
    @Query("SELECT * FROM users WHERE isActive = 1 ORDER BY rating DESC")
    fun getActiveUsers(): Flow<List<User>>
    
    @Query("SELECT * FROM users WHERE role = :role ORDER BY totalServices DESC LIMIT 10")
    fun getTopUsersByRole(role: UserRole): Flow<List<User>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User): Long
    
    @Update
    suspend fun updateUser(user: User)
    
    @Delete
    suspend fun deleteUser(user: User)
}
