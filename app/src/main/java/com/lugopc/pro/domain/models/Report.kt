package com.lugopc.pro.domain.models

import java.time.LocalDateTime

data class ServiceReport(
    val totalServices: Int = 0,
    val totalIncome: Double = 0.0,
    val averageRepairTime: Double = 0.0,
    val completedServices: Int = 0,
    val pendingServices: Int = 0,
    val averageRating: Float = 0f,
    val topTechnician: String = "",
    val mostRepairedEquipment: String = "",
    val period: String = ""
)

data class TechnicianReport(
    val technicianId: String = "",
    val technicianName: String = "",
    val totalServices: Int = 0,
    val completedServices: Int = 0,
    val totalIncome: Double = 0.0,
    val averageRating: Float = 0f,
    val averageRepairTime: Double = 0.0,
    val efficiency: Float = 0f
)

data class InventoryReport(
    val lowStockItems: List<String> = emptyList(),
    val totalItemsValue: Double = 0.0,
    val lastUpdated: LocalDateTime = LocalDateTime.now()
)
