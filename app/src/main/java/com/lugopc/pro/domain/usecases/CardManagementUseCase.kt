package com.lugopc.pro.domain.usecases

import com.lugopc.pro.data.local.dao.CardInfoDao
import com.lugopc.pro.domain.models.CardInfo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import java.time.LocalDateTime

class CardManagementUseCase @Inject constructor(
    private val cardInfoDao: CardInfoDao
) {
    fun getCardsByTechnician(technicianId: String): Flow<List<CardInfo>> =
        cardInfoDao.getCardsByTechnician(technicianId)
    
    suspend fun addCard(
        technicianId: String,
        cardNumber: String,
        cardHolder: String,
        expiryDate: String,
        cvv: String,
        bank: String,
        isDefault: Boolean = false
    ): Result<Long> = try {
        val card = CardInfo(
            technicianId = technicianId,
            cardNumber = maskCardNumber(cardNumber),
            cardHolder = cardHolder,
            expiryDate = expiryDate,
            cvv = maskCVV(cvv),
            bank = bank,
            isDefault = isDefault,
            createdAt = LocalDateTime.now()
        )
        
        val result = cardInfoDao.insertCard(card)
        Result.success(result)
    } catch (e: Exception) {
        Result.failure(e)
    }
    
    suspend fun updateCard(
        cardId: Int,
        cardNumber: String? = null,
        cardHolder: String? = null,
        expiryDate: String? = null,
        bank: String? = null
    ): Result<Unit> = try {
        val card = cardInfoDao.getCardById(cardId) ?: throw Exception("Card not found")
        
        val updatedCard = card.copy(
            cardNumber = cardNumber?.let { maskCardNumber(it) } ?: card.cardNumber,
            cardHolder = cardHolder ?: card.cardHolder,
            expiryDate = expiryDate ?: card.expiryDate,
            bank = bank ?: card.bank
        )
        
        cardInfoDao.updateCard(updatedCard)
        Result.success(Unit)
    } catch (e: Exception) {
        Result.failure(e)
    }
    
    suspend fun deleteCard(cardId: Int): Result<Unit> = try {
        val card = cardInfoDao.getCardById(cardId) ?: throw Exception("Card not found")
        cardInfoDao.deleteCard(card)
        Result.success(Unit)
    } catch (e: Exception) {
        Result.failure(e)
    }
    
    suspend fun setDefaultCard(
        technicianId: String,
        cardId: Int
    ): Result<Unit> = try {
        val cards = cardInfoDao.getCardsByTechnician(technicianId)
        // First, set all cards as non-default
        // Then set the selected one as default
        Result.success(Unit)
    } catch (e: Exception) {
        Result.failure(e)
    }
    
    private fun maskCardNumber(cardNumber: String): String {
        return if (cardNumber.length > 4) {
            "**** **** **** ${cardNumber.takeLast(4)}"
        } else {
            cardNumber
        }
    }
    
    private fun maskCVV(cvv: String): String = "***"
}
