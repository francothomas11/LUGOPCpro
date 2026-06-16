package com.lugopc.pro.data.repository

import com.lugopc.pro.data.local.dao.UserDao
import com.lugopc.pro.domain.models.User
import com.lugopc.pro.domain.models.UserRole
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userDao: UserDao
) {
    suspend fun getUserById(id: String): User? = userDao.getUserById(id)
    
    suspend fun getUserByEmail(email: String): User? = userDao.getUserByEmail(email)
    
    fun getUsersByRole(role: UserRole): Flow<List<User>> = userDao.getUsersByRole(role)
    
    fun getActiveUsers(): Flow<List<User>> = userDao.getActiveUsers()
    
    fun getTopUsersByRole(role: UserRole): Flow<List<User>> = userDao.getTopUsersByRole(role)
    
    suspend fun insertUser(user: User): Long = userDao.insertUser(user)
    
    suspend fun updateUser(user: User) = userDao.updateUser(user)
    
    suspend fun deleteUser(user: User) = userDao.deleteUser(user)
}
