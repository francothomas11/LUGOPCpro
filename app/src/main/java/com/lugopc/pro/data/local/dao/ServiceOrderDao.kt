package com.lugopc.pro.data.local.dao

import androidx.room.*
import com.lugopc.pro.domain.models.ServiceOrder
import com.lugopc.pro.domain.models.ServiceStatus
import kotlinx.coroutines.flow.Flow

@Dao
interface ServiceOrderDao {
    
    @Query("SELECT * FROM service_orders ORDER BY createdAt DESC")
    fun getAllOrders(): Flow<List<ServiceOrder>>
    
    @Query("SELECT * FROM service_orders WHERE status = :status ORDER BY createdAt DESC")
    fun getOrdersByStatus(status: ServiceStatus): Flow<List<ServiceOrder>>
    
    @Query("SELECT * FROM service_orders WHERE id = :id")
    suspend fun getOrderById(id: Int): ServiceOrder?
    
    @Query("SELECT * FROM service_orders WHERE folio = :folio")
    suspend fun getOrderByFolio(folio: String): ServiceOrder?
    
    @Query("SELECT * FROM service_orders WHERE clientPhone = :phoneNumber ORDER BY createdAt DESC")
    fun getOrdersByClientPhone(phoneNumber: String): Flow<List<ServiceOrder>>
    
    @Query("SELECT * FROM service_orders WHERE technicianId = :technicianId ORDER BY createdAt DESC")
    fun getOrdersByTechnician(technicianId: String): Flow<List<ServiceOrder>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrder(order: ServiceOrder): Long
    
    @Update
    suspend fun updateOrder(order: ServiceOrder)
    
    @Delete
    suspend fun deleteOrder(order: ServiceOrder)
    
    @Query("SELECT COUNT(*) FROM service_orders WHERE status = :status")
    suspend fun countOrdersByStatus(status: ServiceStatus): Int
    
    @Query("SELECT MAX(CAST(SUBSTR(folio, 3) AS INTEGER)) FROM service_orders")
    suspend fun getMaxFolioNumber(): Int?
}
