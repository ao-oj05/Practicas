package com.luisperez.cuartitosa_app.presentation.views

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.luisperez.cuartitosa_app.presentation.viewModels.DashboardViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddStudentView(navController: NavController, dashboardViewModel: DashboardViewModel){
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("Agregar estudiante")
                },
                actions = {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
                    }
                }
            )
        }
    ) {
        AddStudentViewContent(it)
    }
}

@Composable
fun AddStudentViewContent(paddingValues: PaddingValues){

}
