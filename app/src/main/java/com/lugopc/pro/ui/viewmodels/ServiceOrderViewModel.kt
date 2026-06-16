package com.lugopc.pro.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lugopc.pro.data.repository.ServiceOrderRepository
import com.lugopc.pro.domain.models.ServiceOrder
import com.lugopc.pro.domain.models.ServiceStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ServiceOrderViewModel @Inject constructor(
    private val repository: ServiceOrderRepository
) : ViewModel() {
    
    private val _orders = MutableStateFlow<List<ServiceOrder>>(emptyList())
    val orders: StateFlow<List<ServiceOrder>> = _orders.asStateFlow()
    
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()
    
    private val _nextFolio = MutableStateFlow("F-001")
    val nextFolio: StateFlow<String> = _nextFolio.asStateFlow()
    
    init {
        loadAllOrders()
        generateNextFolio()
    }
    
    fun loadAllOrders() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                repository.getAllOrders().collect { orders ->
                    _orders.value = orders
                }
            } catch (e: Exception) {
                _errorMessage.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }
    
    fun loadOrdersByStatus(status: ServiceStatus) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                repository.getOrdersByStatus(status).collect { orders ->
                    _orders.value = orders
                }
            } catch (e: Exception) {
                _errorMessage.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }
    
    fun createOrder(order: ServiceOrder) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                repository.insertOrder(order)
                _errorMessage.value = null
                loadAllOrders()
                generateNextFolio()
            } catch (e: Exception) {
                _errorMessage.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }
    
    fun updateOrder(order: ServiceOrder) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                repository.updateOrder(order)
                _errorMessage.value = null
                loadAllOrders()
            } catch (e: Exception) {
                _errorMessage.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }
    
    private fun generateNextFolio() {
        viewModelScope.launch {
            try {
                val folio = repository.getNextFolio()
                _nextFolio.value = folio
            } catch (e: Exception) {
                _nextFolio.value = "F-001"
            }
        }
    }
}
