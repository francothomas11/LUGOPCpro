package com.lugopc.pro.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lugopc.pro.data.repository.InventoryRepository
import com.lugopc.pro.domain.models.InventoryItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InventoryViewModel @Inject constructor(
    private val repository: InventoryRepository
) : ViewModel() {
    
    private val _items = MutableStateFlow<List<InventoryItem>>(emptyList())
    val items: StateFlow<List<InventoryItem>> = _items.asStateFlow()
    
    private val _lowStockItems = MutableStateFlow<List<InventoryItem>>(emptyList())
    val lowStockItems: StateFlow<List<InventoryItem>> = _lowStockItems.asStateFlow()
    
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    
    init {
        loadAllItems()
        loadLowStockItems()
    }
    
    fun loadAllItems() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                repository.getAllItems().collect { items ->
                    _items.value = items
                }
            } finally {
                _isLoading.value = false
            }
        }
    }
    
    fun loadLowStockItems() {
        viewModelScope.launch {
            repository.getLowStockItems().collect { items ->
                _lowStockItems.value = items
            }
        }
    }
    
    fun addItem(item: InventoryItem) {
        viewModelScope.launch {
            repository.insertItem(item)
            loadAllItems()
        }
    }
    
    fun updateItem(item: InventoryItem) {
        viewModelScope.launch {
            repository.updateItem(item)
            loadAllItems()
        }
    }
}
