package com.lugopc.pro.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

enum class ServiceStatus {
    RECEIVED,
    IN_PROGRESS,
    COMPLETED,
    DELIVERED,
    CANCELLED
}

@Entity(tableName = "service_orders")
data class ServiceOrder(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val folio: String = "",
    val clientName: String = "",
    val clientPhone: String = "",
    val clientPassword: String = "",
    val equipmentType: String = "",
    val serviceDescription: String = "",
    val diagnosis: String = "",
    val observations: String = "",
    val accessories: List<String> = emptyList(),
    val status: ServiceStatus = ServiceStatus.RECEIVED,
    val entryPhotos: List<String> = emptyList(),
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now(),
    val technicianId: String = "",
    val technicianName: String = "",
    val estimatedCost: Double = 0.0,
    val finalCost: Double = 0.0,
    val rating: Float = 0f,
    val clientComment: String = "",
    val isOnline: Boolean = true
)
