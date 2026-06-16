package com.lugopc.pro.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "inventory_items")
data class InventoryItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String = "",
    val description: String = "",
    val category: String = "",
    val quantity: Int = 0,
    val minimumQuantity: Int = 0,
    val unitCost: Double = 0.0,
    val supplierCost: Double = 0.0,
    val barcode: String = "",
    val photoUrl: String = "",
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val lastUpdated: LocalDateTime = LocalDateTime.now()
)

@Entity(tableName = "inventory_movements")
data class InventoryMovement(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val itemId: Int = 0,
    val serviceOrderId: Int = 0,
    val quantity: Int = 0,
    val type: String = "", // IN or OUT
    val reason: String = "",
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val technicianId: String = ""
)
