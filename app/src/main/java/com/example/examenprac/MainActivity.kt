package com.example.examenprac

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.rememberNavController
import com.example.examenprac.data.datastore.ThemePreference
import com.example.examenprac.data.local.AppDatabase
import com.example.examenprac.data.repository.FormRepository
import com.example.examenprac.ui.navigation.NavGraph
import com.example.examenprac.ui.theme.ExamenPracTheme
import com.example.examenprac.ui.viewmodel.FormViewModel
import com.example.examenprac.ui.viewmodel.ThemeViewModel
import com.example.examenprac.ui.viewmodel.ThemeViewModelFactory

class MainActivity : ComponentActivity() {

    private val themeViewModel: ThemeViewModel by viewModels {
        ThemeViewModelFactory(ThemePreference(this))
    }

    // 🔹 Mover fuera del setContent para evitar el warning
    private lateinit var formViewModel: FormViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializamos aquí el formViewModel con el repositorio
        val database = AppDatabase.getDatabase(this)
        val repository = FormRepository(database.formDao())
        formViewModel = FormViewModel(repository)

        setContent {
            val isDarkMode by themeViewModel.isDarkTheme.collectAsState(initial = false)
            val navController = rememberNavController()

            ExamenPracTheme(darkTheme = isDarkMode) {
                Surface(color = MaterialTheme.colorScheme.background) {
                    NavGraph(
                        navController = navController,
                        themeViewModel = themeViewModel,
                        formViewModel = formViewModel
                    )
                }
            }
        }
    }
}
