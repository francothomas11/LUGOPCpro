package com.lugopc.pro.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

enum class QuoteStatus {
    PENDING,
    APPROVED,
    REJECTED,
    EXPIRED
}

@Entity(tableName = "quotes")
data class Quote(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val serviceOrderId: Int = 0,
    val clientPhone: String = "",
    val description: String = "",
    val amount: Double = 0.0,
    val status: QuoteStatus = QuoteStatus.PENDING,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val expiresAt: LocalDateTime = LocalDateTime.now(),
    val approvedAt: LocalDateTime? = null,
    val pdfUrl: String? = null
)
