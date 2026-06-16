package com.lugopc.pro.data.local.converters

import androidx.room.TypeConverter
import com.lugopc.pro.domain.models.PaymentMethod
import com.lugopc.pro.domain.models.PaymentStatus
import com.lugopc.pro.domain.models.UserRole
import com.lugopc.pro.domain.models.ServiceStatus
import com.lugopc.pro.domain.models.QuoteStatus
import com.lugopc.pro.domain.models.NotificationType
import com.lugopc.pro.domain.models.AuditAction

class EnumConverters {
    
    @TypeConverter
    fun fromPaymentMethod(value: PaymentMethod?): String? {
        return value?.name
    }
    
    @TypeConverter
    fun toPaymentMethod(value: String?): PaymentMethod? {
        return value?.let { PaymentMethod.valueOf(it) }
    }
    
    @TypeConverter
    fun fromPaymentStatus(value: PaymentStatus?): String? {
        return value?.name
    }
    
    @TypeConverter
    fun toPaymentStatus(value: String?): PaymentStatus? {
        return value?.let { PaymentStatus.valueOf(it) }
    }
    
    @TypeConverter
    fun fromUserRole(value: UserRole?): String? {
        return value?.name
    }
    
    @TypeConverter
    fun toUserRole(value: String?): UserRole? {
        return value?.let { UserRole.valueOf(it) }
    }
    
    @TypeConverter
    fun fromServiceStatus(value: ServiceStatus?): String? {
        return value?.name
    }
    
    @TypeConverter
    fun toServiceStatus(value: String?): ServiceStatus? {
        return value?.let { ServiceStatus.valueOf(it) }
    }
    
    @TypeConverter
    fun fromQuoteStatus(value: QuoteStatus?): String? {
        return value?.name
    }
    
    @TypeConverter
    fun toQuoteStatus(value: String?): QuoteStatus? {
        return value?.let { QuoteStatus.valueOf(it) }
    }
    
    @TypeConverter
    fun fromNotificationType(value: NotificationType?): String? {
        return value?.name
    }
    
    @TypeConverter
    fun toNotificationType(value: String?): NotificationType? {
        return value?.let { NotificationType.valueOf(it) }
    }
    
    @TypeConverter
    fun fromAuditAction(value: AuditAction?): String? {
        return value?.name
    }
    
    @TypeConverter
    fun toAuditAction(value: String?): AuditAction? {
        return value?.let { AuditAction.valueOf(it) }
    }
}
