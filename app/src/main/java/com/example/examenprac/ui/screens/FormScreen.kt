package com.example.examenprac.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.examenprac.data.local.entity.FormEntry
import com.example.examenprac.ui.viewmodel.FormViewModel
import kotlinx.coroutines.launch

@Composable
fun FormScreen(
    formViewModel: FormViewModel,
    onNavigateBack: () -> Unit
) {
    val formList by formViewModel.formList.collectAsState()
    val coroutineScope = rememberCoroutineScope()

    var nombre by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var edad by remember { mutableStateOf("") }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // 🔹 Botón para regresar al Dashboard
            Button(
                onClick = onNavigateBack,
                modifier = Modifier
                    .align(Alignment.Start)
            ) {
                Text("⬅ Volver al Dashboard")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "📝 Formulario de Registro",
                style = MaterialTheme.typography.headlineLarge.copy(
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 26.sp
                )
            )

            Spacer(modifier = Modifier.height(20.dp))

            // 🔹 Campo de nombre (sin autocompletar)
            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    autoCorrect = false,
                    keyboardType = KeyboardType.Text
                )
            )

            Spacer(modifier = Modifier.height(12.dp))

            // 🔹 Campo de correo (sin autocompletar)
            OutlinedTextField(
                value = correo,
                onValueChange = { correo = it },
                label = { Text("Correo electrónico") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    autoCorrect = false,
                    keyboardType = KeyboardType.Email
                )
            )

            Spacer(modifier = Modifier.height(12.dp))

            // 🔹 Campo de edad (sin autocompletar)
            OutlinedTextField(
                value = edad,
                onValueChange = { edad = it },
                label = { Text("Edad") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    autoCorrect = false,
                    keyboardType = KeyboardType.Number
                )
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    if (nombre.isNotBlank() && correo.isNotBlank() && edad.isNotBlank()) {
                        coroutineScope.launch {
                            formViewModel.saveForm(nombre, correo, edad.toIntOrNull() ?: 0)
                            nombre = ""
                            correo = ""
                            edad = ""
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("💾 Guardar")
            }

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = "📋 Registros guardados",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )

            Spacer(modifier = Modifier.height(10.dp))

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(formList) { item ->
                    FormItem(
                        form = item,
                        onDelete = {
                            coroutineScope.launch {
                                formViewModel.deleteForm(item.id)
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun FormItem(form: FormEntry, onDelete: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text("👤 ${form.nombre}", fontWeight = FontWeight.Bold)
                Text("📧 ${form.correo}")
                Text("🎂 Edad: ${form.edad}")
            }

            IconButton(onClick = onDelete) {
                Text("🗑")
            }
        }
    }
}
