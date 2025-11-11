package com.example.examenprac.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.examenprac.ui.viewmodel.ThemeViewModel

@Composable
fun ThemeScreen(
    themeViewModel: ThemeViewModel,
    onBack: () -> Unit
) {
    // usar el nombre correcto y pasar initial
    val isDarkMode by themeViewModel.isDarkTheme.collectAsState(initial = false)

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "🎨 Configuración de Tema",
                style = MaterialTheme.typography.headlineLarge.copy(
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 26.sp
                )
            )

            Spacer(modifier = Modifier.height(40.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = if (isDarkMode) "Modo Oscuro" else "Modo Claro",
                    fontSize = 18.sp,
                    modifier = Modifier.padding(end = 12.dp)
                )

                // onCheckedChange recibe el nuevo valor booleano; lo pasamos al ViewModel
                Switch(
                    checked = isDarkMode,
                    onCheckedChange = { newValue ->
                        themeViewModel.toggleTheme(newValue)
                    }
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            Button(onClick = onBack) {
                Text("⬅ Volver al Dashboard")
            }
        }
    }
}
