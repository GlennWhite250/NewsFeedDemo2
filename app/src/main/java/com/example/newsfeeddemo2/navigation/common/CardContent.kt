package com.example.newsfeeddemo2.navigation.common

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.newsfeeddemo2.R
import com.example.newsfeeddemo2.model.Article
import com.example.newsfeeddemo2.model.Source

@Composable
fun CardListContent(items: LazyPagingItems<Article>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(all = 5.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        items(
            items = items,
            key = {
                it.articleId
            }
        ) {
            it?.let {
                CardItem(article = it)
            }
        }
    }
}

@Composable
fun CardItem(article: Article) {
    val painter = rememberAsyncImagePainter(
        ImageRequest.Builder(LocalContext.current).data(data = article.urlToImage)
            .apply(block = fun ImageRequest.Builder.() {
                placeholder(R.drawable.ic_placeholder)
                error(R.drawable.ic_error_foreground)
                crossfade(durationMillis = 1000)
            }).build()
    )
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .fillMaxSize()
            .clickable {
                val browserIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(article.url)
                )
                ContextCompat.startActivity(context, browserIntent, null)
            },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp,
            pressedElevation = 2.dp,
            focusedElevation = 5.dp
        ),
        border = BorderStroke(width = 2.dp, color = Color.Magenta),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(Color.White)
    ) {
        Row(
            modifier = Modifier.padding(all = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painter,
                contentDescription = "Picture for the article",
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.width(width = 8.dp))
            Column(
                modifier = Modifier.padding(5.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text =  if (article.title == null) {
                        buildAnnotatedString {
                            append("There is nothing here")
                        }
                    } else {
                        buildAnnotatedString {
                            append(article.title)
                        }
                    },
            color = Color.Black,
            maxLines = 1,
            fontWeight = FontWeight.Normal,
            overflow = TextOverflow.Ellipsis
            )
            Text(
                text =
                if (article.author == null){
                    buildAnnotatedString {
                        append("There is nothing here")
                    }
                }else {
                    buildAnnotatedString {
                        append("from: ")
                        append(article.author)
                }

                },
                color = Color.Black,
                maxLines = 1,
                fontWeight = FontWeight.Normal,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = if (article.source.name == null) {
                    buildAnnotatedString {
                        append("There is nothing here")
                    }
                } else {
                    buildAnnotatedString {
                        append("On: ")
                        append(article.source.name)
                    }
                },
                color = Color.Black,
                maxLines = 1,
                fontWeight = FontWeight.Light,
                overflow = TextOverflow.Ellipsis
            )
        }

    }
}
}

@Composable
@Preview
fun CardListContentPreview() {
    CardItem(
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