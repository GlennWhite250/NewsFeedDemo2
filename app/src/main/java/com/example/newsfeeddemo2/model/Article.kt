package com.example.newsfeeddemo2.model


import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.newsfeeddemo2.util.Constants.ARTICLE_TABLE_NAME
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Entity(tableName = ARTICLE_TABLE_NAME)
@Serializable
data class Article(
    @PrimaryKey(autoGenerate = true)
    var articleId: Int = 0,
    @SerialName("author")
    val author: String? = " ",
    @SerialName("content")
    val content: String? = " ",
    @SerialName("description")
    val description: String? = " ",
    @SerialName("publishedAt")
    val publishedAt: String? = " ",
    @Embedded
    @SerialName("source")
    val source: Source,
    @SerialName("title")
    val title: String? = " ",
    @SerialName("url")
    val url: String? = " ",
    @SerialName("urlToImage")
    val urlToImage: String? = " "
)