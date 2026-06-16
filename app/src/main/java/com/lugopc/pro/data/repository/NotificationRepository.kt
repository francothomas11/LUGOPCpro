package com.lugopc.pro.data.repository

import com.lugopc.pro.data.local.dao.NotificationDao
import com.lugopc.pro.domain.models.Notification
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NotificationRepository @Inject constructor(
    private val notificationDao: NotificationDao
) {
    fun getUserNotifications(userId: String): Flow<List<Notification>> =
        notificationDao.getUserNotifications(userId)
    
    fun getUnreadNotifications(userId: String): Flow<List<Notification>> =
        notificationDao.getUnreadNotifications(userId)
    
    fun getUnreadCount(userId: String): Flow<Int> =
        notificationDao.getUnreadCount(userId)
    
    suspend fun insertNotification(notification: Notification): Long =
        notificationDao.insertNotification(notification)
    
    suspend fun updateNotification(notification: Notification) =
        notificationDao.updateNotification(notification)
    
    suspend fun markAsRead(notificationId: Int) =
        notificationDao.markAsRead(notificationId)
    
    suspend fun deleteOldNotifications() =
        notificationDao.deleteOldNotifications()
}
