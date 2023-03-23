package com.example.newsfeeddemo2.screens.home

import androidx.lifecycle.ViewModel
import com.example.newsfeeddemo2.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    repository: Repository
): ViewModel() {
    val getAllArticles = repository.getAllArticles()
}