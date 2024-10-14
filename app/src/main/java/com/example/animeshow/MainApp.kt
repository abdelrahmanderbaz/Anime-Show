package com.example.animeshow

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MainApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "main") {
        composable("main") { MyPhotos(navController) }
        composable("detail/{photoId}") { backStackEntry ->
            val photoId = backStackEntry.arguments?.getString("photoId")?.toInt() ?: 0
            DetailScreen(photoId)
        }
    }

}