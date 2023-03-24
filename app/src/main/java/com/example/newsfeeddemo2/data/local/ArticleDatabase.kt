package com.example.newsfeeddemo2.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.newsfeeddemo2.data.local.dao.ArticleDao
import com.example.newsfeeddemo2.data.local.dao.ArticleRemoteKeysDao
import com.example.newsfeeddemo2.data.local.dao.Converters
import com.example.newsfeeddemo2.model.Article
import com.example.newsfeeddemo2.model.ArticleRemoteKeys

@Database(entities = [Article::class, ArticleRemoteKeys::class], version = 1)
@TypeConverters(Converters::class)
abstract class ArticleDatabase: RoomDatabase() {
    abstract fun articleDao(): ArticleDao
    abstract fun articleRemoteKeysDao(): ArticleRemoteKeysDao
}