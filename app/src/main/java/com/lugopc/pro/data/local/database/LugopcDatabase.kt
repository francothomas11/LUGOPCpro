package com.lugopc.pro.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.lugopc.pro.data.local.converters.DateTimeConverters
import com.lugopc.pro.data.local.converters.ListConverters
import com.lugopc.pro.data.local.converters.EnumConverters
import com.lugopc.pro.data.local.dao.*
import com.lugopc.pro.domain.models.*

@Database(
    entities = [
        ServiceOrder::class,
        User::class,
        Payment::class,
        CardInfo::class,
        Quote::class,
        InventoryItem::class,
        InventoryMovement::class,
        AttendanceSchedule::class,
        Notification::class,
        ChatConversation::class,
        ChatMessage::class,
        AuditLog::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    DateTimeConverters::class,
    ListConverters::class,
    EnumConverters::class
)
abstract class LugopcDatabase : RoomDatabase() {
    abstract fun serviceOrderDao(): ServiceOrderDao
    abstract fun userDao(): UserDao
    abstract fun paymentDao(): PaymentDao
    abstract fun cardInfoDao(): CardInfoDao
    abstract fun quoteDao(): QuoteDao
    abstract fun inventoryDao(): InventoryDao
    abstract fun inventoryMovementDao(): InventoryMovementDao
    abstract fun attendanceScheduleDao(): AttendanceScheduleDao
    abstract fun notificationDao(): NotificationDao
    abstract fun chatDao(): ChatDao
    abstract fun auditLogDao(): AuditLogDao
}
