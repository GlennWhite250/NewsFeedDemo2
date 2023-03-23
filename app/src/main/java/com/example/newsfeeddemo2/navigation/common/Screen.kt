package com.example.newsfeeddemo2.navigation.common

import com.example.newsfeeddemo2.util.Constants.HOME_SCREEN

sealed class Screen(val route: String){
    object Home: Screen(HOME_SCREEN)
}