package com.example.newsfeeddemo2.screens.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.newsfeeddemo2.util.Constants.COUNTRY_CODE_US

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(
    onSearchClicked: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = "Top $COUNTRY_CODE_US Headlines",
                color = Color.Black
            )
        },
        actions = {
            IconButton(onClick = onSearchClicked) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search for something"
                )
            }
        }

    )
}

@Composable
@Preview
fun HomeTopBarPreview(){
    HomeTopBar(){}
}