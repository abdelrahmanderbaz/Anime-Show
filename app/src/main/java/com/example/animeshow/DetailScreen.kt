package com.example.animeshow

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(photoResId: Int) {
    val index = AnimePhotos.indexOf(photoResId)
    val detailedPhotoResId = DetailedAnimePhotos.getOrNull(index) ?: R.drawable.fullmetal // صورة افتراضية لو مفيش حاجة
    val image: Painter = painterResource(id = detailedPhotoResId)
    val name = Name_of_Anime.getOrNull(index) ?: "غير معروف"
    val year = year_of_production.getOrNull(index) ?: "غير معروف"
    val genre = Genre0.getOrNull(index) ?: "غير معروف"
    val episodes = number_of_episodes.getOrNull(index) ?: "غير معروف"

    var comment by remember { mutableStateOf("") }
    var submittedComment by remember { mutableStateOf("") }

    Scaffold(topBar = { TopBar() }) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(Color.Black)
        ) {
            Box {
                Image(
                    painter = image,
                    contentDescription = null,
                    modifier = Modifier.height(320.dp).fillMaxWidth(),
                    contentScale = ContentScale.FillBounds
                )
            }
            Surface(
                modifier = Modifier
                    .padding(top = 7.dp)
                    .height(167.dp)
                    .fillMaxWidth(),
                color = Color(0xFF37474F),
                shape = RoundedCornerShape(8.dp)
            ) {
                Column(modifier = Modifier.padding(8.dp)) {
                    Text(
                        text = "Anime Name: $name",
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        fontSize = 20.sp
                    )
                    Text(
                        text = "Release Year: $year",
                        color = Color.White,
                        fontSize = 18.sp
                    )
                    Text(
                        text = "Anime Type: $genre",
                        color = Color.White,
                        fontSize = 18.sp
                    )
                    Text(
                        text = "Number of Episodes: $episodes",
                        color = Color.White,
                        fontSize = 18.sp
                    )
                }
            }

            // Surface جديدة لتعليق المستخدم
            Surface(
                modifier = Modifier
                    .padding(top = 10.dp)
                    .fillMaxWidth().height(270.dp),
                color = Color(0xFF37474F),
                shape = RoundedCornerShape(8.dp)
            ) {
                Column(modifier = Modifier.padding(8.dp)) {
                    Text(
                        text = "Add a Comment:",
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.height(10.dp))


                    TextField(
                        value = comment,
                        onValueChange = { comment = it },
                        placeholder = { Text("Type your comment here...") },
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                        keyboardActions = KeyboardActions(
                            onDone = {

                                submittedComment = comment
                                comment = ""
                            }
                        ),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.White
                        )
                    )

                    Spacer(modifier = Modifier.height(16.dp))


                    if (submittedComment.isNotEmpty()) {
                        Text(
                            text = "Comment: $submittedComment",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }
                }
            }
        }
    }
}


