package com.example.newsfeeddemo2.data.remote

import com.example.newsfeeddemo2.model.ArticleResponse
import com.example.newsfeeddemo2.util.Constants.API_KEY
import com.example.newsfeeddemo2.util.Constants.COUNTRY_CODE_HT
import com.example.newsfeeddemo2.util.Constants.COUNTRY_CODE_US
import com.example.newsfeeddemo2.util.Constants.HEADLINES_ENDPOINT
import com.example.newsfeeddemo2.util.Constants.ITEMS_PER_PAGE
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticleApi {
    @GET(HEADLINES_ENDPOINT)
    suspend fun getAllArticles(
        @Query("country")
        country: String = COUNTRY_CODE_US,
        @Query("apiKey")
        apiKey: String = API_KEY,
        @Query("pageSize")
        pageSize: Int = ITEMS_PER_PAGE,
        @Query("page")
        page: Int
    ): ArticleResponse
}