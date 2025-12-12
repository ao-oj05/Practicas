package com.example.examu3.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.examu3.views.BuscadorView
import com.example.examu3.views.FavoritosView
import com.example.examu3.views.HomeView
import com.example.examu3.views.ViewDetails

@Composable
fun NavManager(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController = navController, startDestination = "Home", modifier = modifier) {
        composable("Home") {
            HomeView(navController = navController)
        }
        composable("Buscador") {
            BuscadorView(navController = navController)
        }
        composable("Favoritos") {
            FavoritosView(navController = navController)
        }
        composable(
            "ViewDetails/{pokemonName}",
            arguments = listOf(navArgument("pokemonName") { type = NavType.StringType })
        ) {
            val pokemonName = it.arguments?.getString("pokemonName") ?: ""
            ViewDetails(pokemonName = pokemonName, navController = navController)
        }
    }
}