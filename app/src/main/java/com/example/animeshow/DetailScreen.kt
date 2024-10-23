package com.example.animeshow

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import kotlinx.coroutines.launch

@Composable
fun DetailScreen(animeId: String) {
    val coroutineScope = rememberCoroutineScope()
    var anime by remember { mutableStateOf<AnimeData?>(null) }

    // Fetch detailed data based on anime ID
    LaunchedEffect(animeId) {
        coroutineScope.launch {
            try {
                anime = RetrofitInstance.api.getAnimeList().data.firstOrNull { it.id == animeId }
            } catch (e: Exception) {
                // Handle error
            }
        }
    }

    Scaffold(topBar = { TopBar() }) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(Color.Black)
        ) {
            anime?.let { data ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val painter = rememberAsyncImagePainter(model = data.attributes.coverImage.original)
                    Image(
                        painter = painter,
                        contentDescription = null,
                        modifier = Modifier
                            .height(320.dp)
                            .fillMaxWidth(),
                        contentScale = ContentScale.FillBounds
                    )
                    Surface(
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .fillMaxWidth()
                            .height(165.dp),
                        shape = RoundedCornerShape(8.dp),
                        color = Color(0xFF37474F)
                    ) {
                        Column(modifier = Modifier.padding(8.dp)) {
                            Text(
                                text = "Anime Name: ${data.attributes.canonicalTitle}",
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                fontSize = 20.sp
                            )
                            Row()
                            {
                                Text(
                                    text = "Start: ${data.attributes.startDate}  ",
                                    color = Color.White,
                                    fontSize = 17.sp
                                )
                                Text(
                                    text = "End: ${data.attributes.endDate}",
                                    color = Color.White,
                                    fontSize = 17.sp
                                )
                            }
                            Text(
                                text = "Rating: ${data.attributes.averageRating}",
                                color = Color.White,
                                fontSize = 18.sp
                            )
                            Text(
                                text = "Episodes: ${data.attributes.episodeCount}",
                                color = Color.White,
                                fontSize = 18.sp
                            )
                            Text(
                                text = "Age Rating: ${data.attributes.ageRatingGuide}",
                                color = Color.White,
                                fontSize = 18.sp
                            )

                        }
                    }
                    Surface(
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .fillMaxWidth()
                            .height(325.dp),
                        color = Color(0xFF37474F),
                        shape = RoundedCornerShape(8.dp)
                    )
                    {


                        Text(
                            text = "Description: ${data.attributes.description}",
                            color = Color.White,
                            fontSize = 10.sp,
                            modifier=Modifier.padding(6.dp)
                        )
                    }
                }
            }
        }
    }
}



