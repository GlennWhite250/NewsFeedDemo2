package com.example.newsfeeddemo2.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.newsfeeddemo2.util.Constants.ARTICLE_REMOTE_KEYS_TABLE

@Entity(tableName = ARTICLE_REMOTE_KEYS_TABLE)
data class ArticleRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val id: Int?,
    val prevPage: Int?,
    val nextPage: Int?
)