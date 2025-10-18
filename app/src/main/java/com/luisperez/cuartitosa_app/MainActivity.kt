package com.luisperez.cuartitosa_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.luisperez.cuartitosa_app.presentation.navigation.NavManager
import com.luisperez.cuartitosa_app.presentation.viewModels.DashboardViewModel
import com.luisperez.cuartitosa_app.presentation.views.DashboardView
import com.luisperez.cuartitosa_app.ui.theme.CuartitosA_AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dashboardViewModel: DashboardViewModel by viewModels()
        enableEdgeToEdge()
        setContent {
            CuartitosA_AppTheme {
                NavManager(dashboardViewModel)
            }
        }
    }
}
