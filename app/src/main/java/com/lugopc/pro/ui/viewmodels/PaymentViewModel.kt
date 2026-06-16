package com.lugopc.pro.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lugopc.pro.data.repository.PaymentRepository
import com.lugopc.pro.domain.models.Payment
import com.lugopc.pro.domain.models.CardInfo
import com.lugopc.pro.domain.models.PaymentStatus
import com.lugopc.pro.domain.usecases.MercadoPagoUseCase
import com.lugopc.pro.domain.usecases.CardManagementUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PaymentViewModel @Inject constructor(
    private val paymentRepository: PaymentRepository,
    private val mercadoPagoUseCase: MercadoPagoUseCase,
    private val cardManagementUseCase: CardManagementUseCase
) : ViewModel() {
    
    private val _payments = MutableStateFlow<List<Payment>>(emptyList())
    val payments: StateFlow<List<Payment>> = _payments.asStateFlow()
    
    private val _cards = MutableStateFlow<List<CardInfo>>(emptyList())
    val cards: StateFlow<List<CardInfo>> = _cards.asStateFlow()
    
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()
    
    private val _paymentUrl = MutableStateFlow<String?>(null)
    val paymentUrl: StateFlow<String?> = _paymentUrl.asStateFlow()
    
    fun loadPayments() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                paymentRepository.getAllPayments().collect { payments ->
                    _payments.value = payments
                }
            } catch (e: Exception) {
                _errorMessage.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }
    
    fun loadCardsByTechnician(technicianId: String) {
        viewModelScope.launch {
            try {
                paymentRepository.getCardsByTechnician(technicianId).collect { cards ->
                    _cards.value = cards
                }
            } catch (e: Exception) {
                _errorMessage.value = e.message
            }
        }
    }
    
    fun createMercadoPagoPayment(
        amount: Double,
        description: String,
        clientEmail: String,
        clientName: String,
        clientPhone: String,
        orderId: String
    ) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val result = mercadoPagoUseCase.createPaymentPreference(
                    amount = amount,
                    description = description,
                    clientEmail = clientEmail,
                    clientName = clientName,
                    clientPhone = clientPhone,
                    orderId = orderId
                )
                
                result.onSuccess { url ->
                    _paymentUrl.value = url
                    _errorMessage.value = null
                }
                
                result.onFailure { error ->
                    _errorMessage.value = error.message
                }
            } finally {
                _isLoading.value = false
            }
        }
    }
    
    fun addCard(
        technicianId: String,
        cardNumber: String,
        cardHolder: String,
        expiryDate: String,
        cvv: String,
        bank: String
    ) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val result = cardManagementUseCase.addCard(
                    technicianId = technicianId,
                    cardNumber = cardNumber,
                    cardHolder = cardHolder,
                    expiryDate = expiryDate,
                    cvv = cvv,
                    bank = bank
                )
                
                result.onSuccess {
                    _errorMessage.value = null
                    loadCardsByTechnician(technicianId)
                }
                
                result.onFailure { error ->
                    _errorMessage.value = error.message
                }
            } finally {
                _isLoading.value = false
            }
        }
    }
    
    fun updateCard(
        cardId: Int,
        cardNumber: String?,
        cardHolder: String?,
        expiryDate: String?,
        bank: String?
    ) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val result = cardManagementUseCase.updateCard(
                    cardId = cardId,
                    cardNumber = cardNumber,
                    cardHolder = cardHolder,
                    expiryDate = expiryDate,
                    bank = bank
                )
                
                result.onSuccess {
                    _errorMessage.value = null
                }
                
                result.onFailure { error ->
                    _errorMessage.value = error.message
                }
            } finally {
                _isLoading.value = false
            }
        }
    }
    
    fun deleteCard(cardId: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val result = cardManagementUseCase.deleteCard(cardId)
                
                result.onSuccess {
                    _errorMessage.value = null
                    _cards.value = _cards.value.filter { it.id != cardId }
                }
                
                result.onFailure { error ->
                    _errorMessage.value = error.message
                }
            } finally {
                _isLoading.value = false
            }
        }
    }
}
