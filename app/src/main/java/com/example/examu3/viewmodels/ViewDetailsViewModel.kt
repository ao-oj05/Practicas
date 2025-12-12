package com.example.examu3.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.example.examu3.data.local.PokemonFavorite
import com.example.examu3.models.PokemonDetails
import com.example.examu3.repository.PokemonRepository

import javax.inject.Inject

@HiltViewModel
class ViewDetailsViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository
) : ViewModel() {

    val pokemonDetails = mutableStateOf<PokemonDetails?>(null)
    val isFavorite = mutableStateOf(false)
    val isLoading = mutableStateOf(false)
    val error = mutableStateOf<String?>(null)

    fun getPokemonDetails(pokemonName: String) {
        isLoading.value = true
        error.value = null
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val details = pokemonRepository.getPokemonDetails(pokemonName)
                pokemonDetails.value = details
                isFavorite.value = pokemonRepository.isFavorite(details.id)
            } catch (e: Exception) {
                error.value = "Error al cargar los detalles del Pokémon. Verifica tu conexión."
            } finally {
                isLoading.value = false
            }
        }
    }

    fun toggleFavorite() {
        viewModelScope.launch(Dispatchers.IO) {
            pokemonDetails.value?.let {
                if (isFavorite.value) {
                    pokemonRepository.deleteFavorite(
                        PokemonFavorite(
                            id = it.id,
                            name = it.name,
                            imageUrl = "",
                            types = emptyList(),
                            height = 0,
                            weight = 0,
                            stats = emptyList()
                        )
                    )
                } else {
                    pokemonRepository.addFavorite(
                        PokemonFavorite(
                            id = it.id,
                            name = it.name,
                            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${it.id}.png",
                            types = it.types.map { type -> type.type.name },
                            height = it.height,
                            weight = it.weight,
                            stats = it.stats.map { stat -> "${stat.stat.name}: ${stat.base_stat}" }
                        )
                    )
                }
                isFavorite.value = !isFavorite.value
            }
        }
    }
}