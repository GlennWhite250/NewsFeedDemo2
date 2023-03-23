package com.example.newsfeeddemo2.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.newsfeeddemo2.data.local.ArticleDatabase
import com.example.newsfeeddemo2.data.paging.ArticleRemoteMediator
import com.example.newsfeeddemo2.data.remote.ArticleApi
import com.example.newsfeeddemo2.model.Article
import com.example.newsfeeddemo2.util.Constants.ITEMS_PER_PAGE
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class Repository @Inject constructor(
    private val articleApi: ArticleApi,
    private val articleDatabase: ArticleDatabase
) {
    fun getAllArticles(): Flow<PagingData<Article>> {
        val pagingSourceFactory = {
            articleDatabase.articleDao().getAllArticles()
        }
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            remoteMediator = ArticleRemoteMediator(
                articleApi = articleApi,
                articleDatabase = articleDatabase
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }
}