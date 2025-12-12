package com.example.examu3.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.withContext
import com.example.examu3.models.PokemonResult
import com.example.examu3.repository.PokemonRepository
import javax.inject.Inject

@HiltViewModel
class BuscadorViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository
) : ViewModel() {

    val searchTerm = mutableStateOf("")
    val isLoading = mutableStateOf(false)
    val error = mutableStateOf<String?>(null)
    val pokemonList = mutableStateOf(emptyList<PokemonResult>())
    val filteredPokemonList = mutableStateOf(emptyList<PokemonResult>())

    init {
        loadPokemonList()
    }

    private fun loadPokemonList() {
        viewModelScope.launch(Dispatchers.Main) {
            try {
                isLoading.value = true
                val result = withContext(Dispatchers.IO) {
                    pokemonRepository.getAllPokemons()
                }
                pokemonList.value = result
                filteredPokemonList.value = result
            } catch (e: Exception) {
                error.value = "Error al cargar la lista de Pokémon: ${e.localizedMessage}"
            } finally {
                isLoading.value = false
            }
        }
    }

    fun filterPokemons(query: String) {
        searchTerm.value = query
        filteredPokemonList.value = if (query.isBlank()) {
            pokemonList.value
        } else {
            pokemonList.value.filter {
                it.name?.contains(query, ignoreCase = true) == true
            }
        }
    }
}