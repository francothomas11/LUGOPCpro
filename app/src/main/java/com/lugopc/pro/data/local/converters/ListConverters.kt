package com.lugopc.pro.data.local.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ListConverters {
    private val gson = Gson()
    
    @TypeConverter
    fun fromStringList(list: List<String>?): String? {
        return gson.toJson(list)
    }
    
    @TypeConverter
    fun toStringList(value: String?): List<String>? {
        return value?.let {
            val type = object : TypeToken<List<String>>() {}.type
            gson.fromJson(it, type)
        }
    }
}
