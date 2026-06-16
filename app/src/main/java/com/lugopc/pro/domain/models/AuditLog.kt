package com.lugopc.pro.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

enum class AuditAction {
    CREATE,
    UPDATE,
    DELETE,
    VIEW,
    LOGIN,
    LOGOUT,
    PAYMENT,
    EXPORT
}

@Entity(tableName = "audit_logs")
data class AuditLog(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val userId: String = "",
    val action: AuditAction = AuditAction.VIEW,
    val entityType: String = "",
    val entityId: String = "",
    val details: String = "",
    val timestamp: LocalDateTime = LocalDateTime.now(),
    val ipAddress: String = ""
)
