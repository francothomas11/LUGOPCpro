package com.lugopc.pro.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

enum class PaymentMethod {
    CREDIT_CARD,
    DEBIT_CARD,
    MERCADO_PAGO,
    CASH,
    TRANSFER
}

enum class PaymentStatus {
    PENDING,
    COMPLETED,
    FAILED,
    REFUNDED,
    CANCELLED
}

@Entity(tableName = "payments")
data class Payment(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val serviceOrderId: Int = 0,
    val amount: Double = 0.0,
    val method: PaymentMethod = PaymentMethod.CREDIT_CARD,
    val status: PaymentStatus = PaymentStatus.PENDING,
    val transactionId: String = "",
    val mercadoPagoId: String? = null,
    val cardLastDigits: String = "",
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val completedAt: LocalDateTime? = null,
    val description: String = ""
)

@Entity(tableName = "card_info")
data class CardInfo(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val technicianId: String = "",
    val cardNumber: String = "",
    val cardHolder: String = "",
    val expiryDate: String = "",
    val cvv: String = "",
    val bank: String = "",
    val isDefault: Boolean = false,
    val createdAt: LocalDateTime = LocalDateTime.now()
)
