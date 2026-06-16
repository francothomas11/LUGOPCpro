package com.lugopc.pro.data.repository

import com.lugopc.pro.data.local.dao.ServiceOrderDao
import com.lugopc.pro.domain.models.ServiceOrder
import com.lugopc.pro.domain.models.ServiceStatus
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ServiceOrderRepository @Inject constructor(
    private val serviceOrderDao: ServiceOrderDao
) {
    fun getAllOrders(): Flow<List<ServiceOrder>> = serviceOrderDao.getAllOrders()
    
    fun getOrdersByStatus(status: ServiceStatus): Flow<List<ServiceOrder>> =
        serviceOrderDao.getOrdersByStatus(status)
    
    suspend fun getOrderById(id: Int): ServiceOrder? = serviceOrderDao.getOrderById(id)
    
    suspend fun getOrderByFolio(folio: String): ServiceOrder? = serviceOrderDao.getOrderByFolio(folio)
    
    fun getOrdersByClientPhone(phoneNumber: String): Flow<List<ServiceOrder>> =
        serviceOrderDao.getOrdersByClientPhone(phoneNumber)
    
    fun getOrdersByTechnician(technicianId: String): Flow<List<ServiceOrder>> =
        serviceOrderDao.getOrdersByTechnician(technicianId)
    
    suspend fun insertOrder(order: ServiceOrder): Long = serviceOrderDao.insertOrder(order)
    
    suspend fun updateOrder(order: ServiceOrder) = serviceOrderDao.updateOrder(order)
    
    suspend fun deleteOrder(order: ServiceOrder) = serviceOrderDao.deleteOrder(order)
    
    suspend fun getNextFolio(): String {
        val maxNumber = serviceOrderDao.getMaxFolioNumber() ?: 0
        return "F-${String.format("%03d", maxNumber + 1)}"
    }
    
    suspend fun countOrdersByStatus(status: ServiceStatus): Int =
        serviceOrderDao.countOrdersByStatus(status)
}
