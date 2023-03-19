package com.example.newsfeeddemo2.di

import android.content.Context
import androidx.room.Room
import com.example.newsfeeddemo2.data.local.ArticleDatabase
import com.example.newsfeeddemo2.util.Constants.ARTICLE_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): ArticleDatabase{
        return Room.databaseBuilder(
            context,
            ArticleDatabase::class.java,
            ARTICLE_DATABASE
        ).build()
    }
}