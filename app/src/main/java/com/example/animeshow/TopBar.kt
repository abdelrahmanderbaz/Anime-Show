package com.example.animeshow

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        title = {

            Text(
                text = "Anime Show",
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White
            )
        },
        actions = {

            val videoIcon: Painter = painterResource(id = R.drawable.icon_video)
            IconButton(onClick = {  }) {
                Image(
                    painter = videoIcon,
                    contentDescription = "Video Icon",
                    modifier = Modifier.fillMaxHeight(0.7f)
                )
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor =  Color(0xFF001F3F)
        )
    )
}