package com.lugopc.pro.data.local.dao

import androidx.room.*
import com.lugopc.pro.domain.models.ChatMessage
import com.lugopc.pro.domain.models.ChatConversation
import kotlinx.coroutines.flow.Flow

@Dao
interface ChatDao {
    
    @Query("SELECT * FROM chat_conversations WHERE id = :conversationId ORDER BY lastMessageTime DESC")
    suspend fun getConversationById(conversationId: Int): ChatConversation?
    
    @Query("SELECT * FROM chat_conversations WHERE serviceOrderId = :serviceOrderId")
    fun getConversationByServiceOrder(serviceOrderId: Int): Flow<ChatConversation?>
    
    @Query("SELECT * FROM chat_conversations WHERE clientId = :clientId ORDER BY lastMessageTime DESC")
    fun getClientConversations(clientId: String): Flow<List<ChatConversation>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertConversation(conversation: ChatConversation): Long
    
    @Update
    suspend fun updateConversation(conversation: ChatConversation)
    
    @Query("SELECT * FROM chat_messages WHERE conversationId = :conversationId ORDER BY timestamp ASC")
    fun getMessages(conversationId: Int): Flow<List<ChatMessage>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMessage(message: ChatMessage): Long
    
    @Update
    suspend fun updateMessage(message: ChatMessage)
    
    @Query("UPDATE chat_messages SET isRead = 1 WHERE conversationId = :conversationId")
    suspend fun markMessagesAsRead(conversationId: Int)
}
