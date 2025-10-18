package com.luisperez.cuartitosa_app.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.luisperez.cuartitosa_app.presentation.viewModels.DashboardViewModel
import com.luisperez.cuartitosa_app.presentation.views.AddStudentView
import com.luisperez.cuartitosa_app.presentation.views.DashboardView
import com.luisperez.cuartitosa_app.presentation.views.DetailsView

@Composable
fun NavManager(dashboardViewModel: DashboardViewModel){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "Home"
    ){
        composable("Home"){
            DashboardView(navController, dashboardViewModel)
        }
        composable("Details/{id}", arguments = listOf(
            navArgument("id"){
                type = NavType.LongType
            },
            navArgument("dashboardViewModel"){
                type = NavType.
            }
        )) {
            val id =it.arguments?.getLong("id")?:0L
            DetailsView(navController, id)
        }
        composable("AddStudent") {
            AddStudentView(navController, dashboardViewModel)
        }
    }

}