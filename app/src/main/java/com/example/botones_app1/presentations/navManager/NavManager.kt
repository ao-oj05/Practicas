package com.luisperez.segundointento.presentations.navManager

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.luisperez.segundointento.presentations.views.DetailsView
import com.luisperez.segundointento.presentations.views.HomeView

@Composable
fun NavManager(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "Home"
    ){
        composable("Home") {
            HomeView(navController)
        }
        composable("Details") {
            DetailsView(navController)
        }
    }
}