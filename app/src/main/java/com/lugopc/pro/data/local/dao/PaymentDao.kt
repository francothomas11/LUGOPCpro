package com.lugopc.pro.data.local.dao

import androidx.room.*
import com.lugopc.pro.domain.models.Payment
import com.lugopc.pro.domain.models.PaymentStatus
import com.lugopc.pro.domain.models.CardInfo
import kotlinx.coroutines.flow.Flow

@Dao
interface PaymentDao {
    
    @Query("SELECT * FROM payments WHERE id = :id")
    suspend fun getPaymentById(id: Int): Payment?
    
    @Query("SELECT * FROM payments WHERE serviceOrderId = :serviceOrderId")
    fun getPaymentsByServiceOrderId(serviceOrderId: Int): Flow<List<Payment>>
    
    @Query("SELECT * FROM payments WHERE status = :status ORDER BY createdAt DESC")
    fun getPaymentsByStatus(status: PaymentStatus): Flow<List<Payment>>
    
    @Query("SELECT * FROM payments ORDER BY createdAt DESC")
    fun getAllPayments(): Flow<List<Payment>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPayment(payment: Payment): Long
    
    @Update
    suspend fun updatePayment(payment: Payment)
    
    @Delete
    suspend fun deletePayment(payment: Payment)
    
    @Query("SELECT SUM(amount) FROM payments WHERE status = :status")
    suspend fun getTotalAmountByStatus(status: PaymentStatus): Double?
}

@Dao
interface CardInfoDao {
    
    @Query("SELECT * FROM card_info WHERE technicianId = :technicianId")
    fun getCardsByTechnician(technicianId: String): Flow<List<CardInfo>>
    
    @Query("SELECT * FROM card_info WHERE id = :id")
    suspend fun getCardById(id: Int): CardInfo?
    
    @Query("SELECT * FROM card_info WHERE isDefault = 1 AND technicianId = :technicianId")
    suspend fun getDefaultCard(technicianId: String): CardInfo?
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCard(card: CardInfo): Long
    
    @Update
    suspend fun updateCard(card: CardInfo)
    
    @Delete
    suspend fun deleteCard(card: CardInfo)
}
