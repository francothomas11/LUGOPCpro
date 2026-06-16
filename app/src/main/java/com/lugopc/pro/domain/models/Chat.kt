package com.lugopc.pro.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "chat_conversations")
data class ChatConversation(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val serviceOrderId: Int = 0,
    val clientId: String = "",
    val technicianId: String = "",
    val lastMessage: String = "",
    val lastMessageTime: LocalDateTime = LocalDateTime.now(),
    val unreadCount: Int = 0,
    val createdAt: LocalDateTime = LocalDateTime.now()
)

@Entity(tableName = "chat_messages")
data class ChatMessage(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val conversationId: Int = 0,
    val senderId: String = "",
    val senderName: String = "",
    val message: String = "",
    val mediaUrl: String? = null,
    val mediaType: String? = null, // IMAGE, VIDEO, AUDIO
    val timestamp: LocalDateTime = LocalDateTime.now(),
    val isRead: Boolean = false
)
