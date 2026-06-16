package com.lugopc.pro.di

import android.content.Context
import androidx.room.Room
import com.lugopc.pro.data.local.database.LugopcDatabase
import com.lugopc.pro.data.local.dao.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    
    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ): LugopcDatabase {
        return Room.databaseBuilder(
            context,
            LugopcDatabase::class.java,
            "lugopc_database"
        ).build()
    }
    
    @Singleton
    @Provides
    fun provideServiceOrderDao(database: LugopcDatabase): ServiceOrderDao {
        return database.serviceOrderDao()
    }
    
    @Singleton
    @Provides
    fun provideUserDao(database: LugopcDatabase): UserDao {
        return database.userDao()
    }
    
    @Singleton
    @Provides
    fun providePaymentDao(database: LugopcDatabase): PaymentDao {
        return database.paymentDao()
    }
    
    @Singleton
    @Provides
    fun provideCardInfoDao(database: LugopcDatabase): CardInfoDao {
        return database.cardInfoDao()
    }
    
    @Singleton
    @Provides
    fun provideQuoteDao(database: LugopcDatabase): QuoteDao {
        return database.quoteDao()
    }
    
    @Singleton
    @Provides
    fun provideInventoryDao(database: LugopcDatabase): InventoryDao {
        return database.inventoryDao()
    }
    
    @Singleton
    @Provides
    fun provideInventoryMovementDao(database: LugopcDatabase): InventoryMovementDao {
        return database.inventoryMovementDao()
    }
    
    @Singleton
    @Provides
    fun provideAttendanceScheduleDao(database: LugopcDatabase): AttendanceScheduleDao {
        return database.attendanceScheduleDao()
    }
    
    @Singleton
    @Provides
    fun provideNotificationDao(database: LugopcDatabase): NotificationDao {
        return database.notificationDao()
    }
    
    @Singleton
    @Provides
    fun provideChatDao(database: LugopcDatabase): ChatDao {
        return database.chatDao()
    }
    
    @Singleton
    @Provides
    fun provideAuditLogDao(database: LugopcDatabase): AuditLogDao {
        return database.auditLogDao()
    }
}
