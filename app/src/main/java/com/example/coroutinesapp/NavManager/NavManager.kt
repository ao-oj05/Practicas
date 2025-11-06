package com.luisperez.coroutinesappa.navManager

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.luisperez.coroutinesappa.viewModels.CoroutinesViewModel
import com.luisperez.coroutinesappa.views.ButtonsView
import com.luisperez.coroutinesappa.views.DashboardView

@Composable
fun NavManager(viewModel: CoroutinesViewModel){
    val controller = rememberNavController()
    NavHost(
        navController = controller,
        startDestination = "Home"
    ){
        composable("Home") {
            DashboardView(controller)
        }
        composable("ButtonsView") {
            ButtonsView(controller, viewModel)
        }
    }
}