package com.lugopc.pro.data.local.converters

import androidx.room.TypeConverter
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class DateTimeConverters {
    private val formatter = DateTimeFormatter.ISO_DATE_TIME
    
    @TypeConverter
    fun fromLocalDateTime(dateTime: LocalDateTime?): String? {
        return dateTime?.format(formatter)
    }
    
    @TypeConverter
    fun toLocalDateTime(value: String?): LocalDateTime? {
        return value?.let { LocalDateTime.parse(it, formatter) }
    }
    
    @TypeConverter
    fun fromLocalTime(time: LocalTime?): String? {
        return time?.toString()
    }
    
    @TypeConverter
    fun toLocalTime(value: String?): LocalTime? {
        return value?.let { LocalTime.parse(it) }
    }
}
