package com.lugopc.pro.data.repository

import com.lugopc.pro.data.local.dao.InventoryDao
import com.lugopc.pro.data.local.dao.InventoryMovementDao
import com.lugopc.pro.domain.models.InventoryItem
import com.lugopc.pro.domain.models.InventoryMovement
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class InventoryRepository @Inject constructor(
    private val inventoryDao: InventoryDao,
    private val movementDao: InventoryMovementDao
) {
    fun getAllItems(): Flow<List<InventoryItem>> = inventoryDao.getAllItems()
    
    fun getLowStockItems(): Flow<List<InventoryItem>> = inventoryDao.getLowStockItems()
    
    suspend fun getItemById(id: Int): InventoryItem? = inventoryDao.getItemById(id)
    
    fun getItemsByCategory(category: String): Flow<List<InventoryItem>> =
        inventoryDao.getItemsByCategory(category)
    
    suspend fun insertItem(item: InventoryItem): Long = inventoryDao.insertItem(item)
    
    suspend fun updateItem(item: InventoryItem) = inventoryDao.updateItem(item)
    
    suspend fun deleteItem(item: InventoryItem) = inventoryDao.deleteItem(item)
    
    // Inventory Movements
    fun getMovementsByServiceOrder(serviceOrderId: Int): Flow<List<InventoryMovement>> =
        movementDao.getMovementsByServiceOrder(serviceOrderId)
    
    fun getMovementsByItem(itemId: Int): Flow<List<InventoryMovement>> =
        movementDao.getMovementsByItem(itemId)
    
    suspend fun insertMovement(movement: InventoryMovement): Long =
        movementDao.insertMovement(movement)
    
    suspend fun getTotalUsedQuantity(itemId: Int): Int =
        movementDao.getTotalUsedQuantity(itemId) ?: 0
}
