package com.example.newsfeeddemo2.navigation.common

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.newsfeeddemo2.R
import com.example.newsfeeddemo2.model.Article
import com.example.newsfeeddemo2.model.Source

@Composable
fun ListContent(items: LazyPagingItems<Article>) {
    Log.d("Test", items.loadState.toString())
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(all = 12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(
            items = items,
            key = { article ->
                article.articleId
            }
        ) { article ->
            article?.let { ArticleItem(article = it) }
        }
    }
}

@Composable
fun ArticleItem(article: Article) {
    val painter = rememberAsyncImagePainter(
        ImageRequest.Builder(LocalContext.current).data(data = article.urlToImage).apply(block = fun ImageRequest.Builder.() {
            placeholder(R.drawable.ic_placeholder)
            error(R.drawable.ic_error_foreground)
            crossfade(durationMillis = 1000)
        }).build()
    )
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .clickable {
                val browserIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(article.url)
                )
                startActivity(context, browserIntent, null)
            }
            .height(300.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Image(
            painter = painter,
            contentDescription = "Article Pic",
            contentScale = ContentScale.Fit,
            modifier = Modifier.fillMaxSize()
        )
        Surface(
            modifier = Modifier
                .height(40.dp)
                .fillMaxWidth(),
            color = Color.Black
        ) {

        }
        Row(
            modifier = Modifier
                .height(40.dp)
                .fillMaxWidth()
                .padding(horizontal = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = buildAnnotatedString {
                    append("Tittle: ")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append(article.title)
                    }
                    append(" from: ")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append(article.author)
                    }
                },
                color = Color.White,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
@Preview
fun ArticlePreview(){
    ArticleItem(
        article = Article(
            articleId = 1,
            author = "Glenn L. White",
            content = "Preview Article",
            description = "Fake news",
            publishedAt = "On the Internet",
            source = Source(
                id = "Glenn L. White",
                name = "Glenn L. White"
            ),
            title = "Fake News Headline",
            url = "",
            urlToImage = ""
        )
    )

}