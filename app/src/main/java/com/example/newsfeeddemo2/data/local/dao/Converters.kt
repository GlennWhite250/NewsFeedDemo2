package com.example.newsfeeddemo2.data.local.dao

import androidx.room.TypeConverter
import com.example.newsfeeddemo2.model.Source

class Converters {
    @TypeConverter
    fun fromSource(source: Source): String{
        return source.name
    }

    @TypeConverter
    fun toSource(name: String): Source {
        return Source(name, name)
    }
}