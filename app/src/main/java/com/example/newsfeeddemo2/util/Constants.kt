package com.example.newsfeeddemo2.util

import com.example.newsfeeddemo2.BuildConfig

object Constants {
    const val ARTICLE_TABLE_NAME = "article_table_name"
    const val ARTICLE_REMOTE_KEYS_TABLE = "article_remote_keys_table"
    const val HEADLINES_ENDPOINT = "/v2/top-headlines"
    const val COUNTRY_CODE_US = "us"
    const val COUNTRY_CODE_HT = "ro"
    const val API_KEY = BuildConfig.API_KEY
    const val ITEMS_PER_PAGE = 20
    const val BASE_URL = "https://newsapi.org"
    const val ARTICLE_DATABASE = "article_database"
    const val HOME_SCREEN = "home_screen"
}