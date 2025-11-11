package com.example.examenprac.ui.viewmodel

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.examenprac.ui.screens.FormScreen

@Composable
fun AppNav(
    navController: NavHostController,
    formViewModel: FormViewModel
) {
    NavHost(navController = navController, startDestination = "form") {
        composable("form") {
            FormScreen(
                formViewModel = formViewModel,
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}
