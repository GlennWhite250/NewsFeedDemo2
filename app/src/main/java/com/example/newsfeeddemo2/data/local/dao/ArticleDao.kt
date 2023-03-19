package com.example.newsfeeddemo2.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newsfeeddemo2.model.Article
import com.example.newsfeeddemo2.util.Constants.ARTICLE_TABLE_NAME

@Dao
interface ArticleDao {
    //Another way to do this might be to return a LiveData<List<Article>>
    @Query("SELECT * FROM $ARTICLE_TABLE_NAME")
    fun getAllArticles(): PagingSource<Int, Article>

    //Might need to add a Long return type here
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addArticles(articles: List<Article>)

    @Query("DELETE FROM $ARTICLE_TABLE_NAME")
    suspend fun deleteAllArticles()
}