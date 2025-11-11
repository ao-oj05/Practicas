package com.example.examenprac.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.examenprac.ui.screens.DashboardScreen
import com.example.examenprac.ui.screens.FormScreen
import com.example.examenprac.ui.screens.ThemeScreen
import com.example.examenprac.ui.viewmodel.FormViewModel
import com.example.examenprac.ui.viewmodel.ThemeViewModel

@Composable
fun NavGraph(
    navController: NavHostController,
    themeViewModel: ThemeViewModel,
    formViewModel: FormViewModel
) {
    NavHost(
        navController = navController,
        startDestination = "dashboard"
    ) {
        composable("dashboard") {
            DashboardScreen(
                onNavigateToForm = { navController.navigate("form") },
                onNavigateToTheme = { navController.navigate("theme") }
            )
        }

        composable("form") {
            FormScreen(
                formViewModel = formViewModel,
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable("theme") {
            ThemeScreen(
                themeViewModel = themeViewModel,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
