package com.example.newsfeeddemo2.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.newsfeeddemo2.data.local.ArticleDatabase
import com.example.newsfeeddemo2.data.remote.ArticleApi
import com.example.newsfeeddemo2.model.Article
import com.example.newsfeeddemo2.model.ArticleRemoteKeys

@OptIn(ExperimentalPagingApi::class)
class ArticleRemoteMediator(
    private val articleApi: ArticleApi,
    private val articleDatabase: ArticleDatabase
): RemoteMediator<Int, Article>() {
    private val articleDao = articleDatabase.articleDao()
    private val articleRemoteKeysDao = articleDatabase.articleRemoteKeysDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Article>
    ): MediatorResult {
        return try {
            val currentPage = when(loadType){
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosetToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: 1
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeysForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage ?: return MediatorResult.Success(
                        endOfPaginationReached = remoteKeys != null
                    )
                    prevPage
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastTiem(state)
                    val nextPage = remoteKeys?.nextPage ?: return MediatorResult.Success(
                        endOfPaginationReached = remoteKeys != null
                    )
                    nextPage
                }
            }

            val response = articleApi.getAllArticles(page = currentPage).articles
            val endOfPaginationReached = response.isEmpty()

            val prevPage = if (currentPage == 1) null else currentPage - 1
            val nextPage = if(endOfPaginationReached) null else currentPage + 1

            articleDatabase.withTransaction {
                if(loadType == LoadType.REFRESH){
                    articleDao.deleteAllArticles()
                    articleRemoteKeysDao.deleteAllRemoteKeys()
                }
                val keys = response.map {
                    ArticleRemoteKeys(
                        id = it.articleId,
                        prevPage = prevPage,
                        nextPage = nextPage
                    )
                }
                articleRemoteKeysDao.addAllRemoteKeys(remoteKeys = keys)
                articleDao.addArticles(articles = response)
            }
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: Exception){
            return MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosetToCurrentPosition(state: PagingState<Int, Article>): ArticleRemoteKeys?{
        return state.anchorPosition?.let {
            state.closestItemToPosition(it)?.articleId?.let {
                articleRemoteKeysDao.getRemoteKeys(id = it)
            }
        }
    }

    private suspend fun getRemoteKeysForFirstItem(state: PagingState<Int, Article>): ArticleRemoteKeys?{
        return state.pages.firstOrNull {
            it.data.isNotEmpty()
        }?.data?.firstOrNull()?.let {
            articleRemoteKeysDao.getRemoteKeys(id = it.articleId)
        }
    }

    private suspend fun getRemoteKeyForLastTiem(state: PagingState<Int, Article>): ArticleRemoteKeys?{
        return state.pages.lastOrNull {
            it.data.isNotEmpty()
        }?.data?.lastOrNull()?.let {
            articleRemoteKeysDao.getRemoteKeys(id = it.articleId)
        }
    }
}