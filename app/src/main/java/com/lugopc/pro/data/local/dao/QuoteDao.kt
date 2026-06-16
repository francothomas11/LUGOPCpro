package com.lugopc.pro.data.local.dao

import androidx.room.*
import com.lugopc.pro.domain.models.Quote
import com.lugopc.pro.domain.models.QuoteStatus
import kotlinx.coroutines.flow.Flow

@Dao
interface QuoteDao {
    
    @Query("SELECT * FROM quotes WHERE id = :id")
    suspend fun getQuoteById(id: Int): Quote?
    
    @Query("SELECT * FROM quotes WHERE serviceOrderId = :serviceOrderId")
    fun getQuotesByServiceOrderId(serviceOrderId: Int): Flow<List<Quote>>
    
    @Query("SELECT * FROM quotes WHERE status = :status ORDER BY createdAt DESC")
    fun getQuotesByStatus(status: QuoteStatus): Flow<List<Quote>>
    
    @Query("SELECT * FROM quotes ORDER BY createdAt DESC")
    fun getAllQuotes(): Flow<List<Quote>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuote(quote: Quote): Long
    
    @Update
    suspend fun updateQuote(quote: Quote)
    
    @Delete
    suspend fun deleteQuote(quote: Quote)
}
