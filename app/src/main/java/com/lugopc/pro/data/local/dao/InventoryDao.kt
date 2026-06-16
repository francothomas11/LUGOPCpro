package com.lugopc.pro.data.local.dao

import androidx.room.*
import com.lugopc.pro.domain.models.InventoryItem
import com.lugopc.pro.domain.models.InventoryMovement
import kotlinx.coroutines.flow.Flow

@Dao
interface InventoryDao {
    
    @Query("SELECT * FROM inventory_items")
    fun getAllItems(): Flow<List<InventoryItem>>
    
    @Query("SELECT * FROM inventory_items WHERE quantity <= minimumQuantity")
    fun getLowStockItems(): Flow<List<InventoryItem>>
    
    @Query("SELECT * FROM inventory_items WHERE id = :id")
    suspend fun getItemById(id: Int): InventoryItem?
    
    @Query("SELECT * FROM inventory_items WHERE category = :category")
    fun getItemsByCategory(category: String): Flow<List<InventoryItem>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: InventoryItem): Long
    
    @Update
    suspend fun updateItem(item: InventoryItem)
    
    @Delete
    suspend fun deleteItem(item: InventoryItem)
}

@Dao
interface InventoryMovementDao {
    
    @Query("SELECT * FROM inventory_movements WHERE serviceOrderId = :serviceOrderId")
    fun getMovementsByServiceOrder(serviceOrderId: Int): Flow<List<InventoryMovement>>
    
    @Query("SELECT * FROM inventory_movements WHERE itemId = :itemId ORDER BY createdAt DESC")
    fun getMovementsByItem(itemId: Int): Flow<List<InventoryMovement>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovement(movement: InventoryMovement): Long
    
    @Query("SELECT SUM(quantity) FROM inventory_movements WHERE itemId = :itemId AND type = 'OUT'")
    suspend fun getTotalUsedQuantity(itemId: Int): Int?
}
