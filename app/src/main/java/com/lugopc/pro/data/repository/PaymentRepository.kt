package com.lugopc.pro.data.repository

import com.lugopc.pro.data.local.dao.PaymentDao
import com.lugopc.pro.data.local.dao.CardInfoDao
import com.lugopc.pro.domain.models.Payment
import com.lugopc.pro.domain.models.PaymentStatus
import com.lugopc.pro.domain.models.CardInfo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PaymentRepository @Inject constructor(
    private val paymentDao: PaymentDao,
    private val cardInfoDao: CardInfoDao
) {
    fun getPaymentsByServiceOrderId(serviceOrderId: Int): Flow<List<Payment>> =
        paymentDao.getPaymentsByServiceOrderId(serviceOrderId)
    
    fun getPaymentsByStatus(status: PaymentStatus): Flow<List<Payment>> =
        paymentDao.getPaymentsByStatus(status)
    
    fun getAllPayments(): Flow<List<Payment>> = paymentDao.getAllPayments()
    
    suspend fun getPaymentById(id: Int): Payment? = paymentDao.getPaymentById(id)
    
    suspend fun insertPayment(payment: Payment): Long = paymentDao.insertPayment(payment)
    
    suspend fun updatePayment(payment: Payment) = paymentDao.updatePayment(payment)
    
    suspend fun deletePayment(payment: Payment) = paymentDao.deletePayment(payment)
    
    suspend fun getTotalAmountByStatus(status: PaymentStatus): Double =
        paymentDao.getTotalAmountByStatus(status) ?: 0.0
    
    // Card Info operations
    fun getCardsByTechnician(technicianId: String): Flow<List<CardInfo>> =
        cardInfoDao.getCardsByTechnician(technicianId)
    
    suspend fun getCardById(id: Int): CardInfo? = cardInfoDao.getCardById(id)
    
    suspend fun getDefaultCard(technicianId: String): CardInfo? =
        cardInfoDao.getDefaultCard(technicianId)
    
    suspend fun insertCard(card: CardInfo): Long = cardInfoDao.insertCard(card)
    
    suspend fun updateCard(card: CardInfo) = cardInfoDao.updateCard(card)
    
    suspend fun deleteCard(card: CardInfo) = cardInfoDao.deleteCard(card)
}
