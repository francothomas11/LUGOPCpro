package com.lugopc.pro.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

enum class NotificationType {
    SERVICE_STATUS,
    PAYMENT_REMINDER,
    QUOTE_APPROVAL,
    NEW_SERVICE,
    TECHNICIAN_ASSIGNMENT,
    SERVICE_COMPLETED
}

@Entity(tableName = "notifications")
data class Notification(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val userId: String = "",
    val type: NotificationType = NotificationType.SERVICE_STATUS,
    val title: String = "",
    val message: String = "",
    val relatedServiceOrderId: Int = 0,
    val isRead: Boolean = false,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val sentViaWhatsApp: Boolean = false,
    val sentViaPush: Boolean = true
)
