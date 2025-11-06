package com.luisperez.coroutinesappa.viewModels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CoroutinesViewModel : ViewModel() {

    val isLoading = mutableStateOf(false)
    val result = mutableStateOf("Pulsa un botón para empezar")

    fun functionOne() {
        viewModelScope.launch {
            setLoading(true)
            result.value = "Ejecutando Función 1..."
            delay(1500)
            result.value = "Función 1 completada!"
            setLoading(false)
        }
    }

    fun functionTwo() {
        setLoading(true)
        result.value = "Ejecutando Función 2 en hilo de fondo..."
        viewModelScope.launch(Dispatchers.IO) {
            delay(3000)
            withContext(Dispatchers.Main) {
                result.value = "Función 2 completada después de 3s!"
                setLoading(false)
            }
        }
    }

    fun functionThree() {
        setLoading(true)
        result.value = "Ejecutando Función 3..."
        viewModelScope.launch {
            delay(500)
            result.value = "Función 3 (Rápida) completada!"
            setLoading(false)
        }
    }

    private fun setLoading(loading: Boolean) {
        isLoading.value = loading
    }
}