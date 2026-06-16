package com.lugopc.pro.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

enum class UserRole {
    CLIENT,
    TECHNICIAN,
    MANAGER,
    ADMIN
}

@Entity(tableName = "users")
data class User(
    @PrimaryKey
    val id: String = "",
    val name: String = "",
    val email: String = "",
    val phoneNumber: String = "",
    val password: String = "",
    val role: UserRole = UserRole.CLIENT,
    val isActive: Boolean = true,
    val photoUrl: String = "",
    val rating: Float = 0f,
    val totalServices: Int = 0,
    val specialization: String = "",
    val createdAt: Long = System.currentTimeMillis()
)
