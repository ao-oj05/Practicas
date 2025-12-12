package com.example.examu3.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import com.example.examu3.data.local.PokemonFavorite
import com.example.examu3.models.PokemonDetails
import com.example.examu3.repository.PokemonRepository

import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository
) : ViewModel() {

    val pokemons = mutableStateOf<List<PokemonDetails>>(emptyList())
    private var offset = 0
    private val limit = 10
    val isLoading = mutableStateOf(false)
    val error = mutableStateOf<String?>(null)

    val favoritePokemons = pokemonRepository.getFavoritePokemons()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    init {
        getPokemons()
    }

    fun getPokemons() {
        if (isLoading.value) return
        isLoading.value = true
        error.value = null
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = pokemonRepository.getPokemons(limit, offset)
                val newPokemons = response.results.mapNotNull { result ->
                    result.name?.let { name ->
                        pokemonRepository.getPokemonDetails(name)
                    }
                }
                pokemons.value += newPokemons
                offset += limit
            } catch (e: Exception) {
                error.value = "Error de red. Verifica tu conexión."
            } finally {
                isLoading.value = false
            }
        }
    }

    fun isFavorite(pokemon: PokemonDetails): Boolean {
        return favoritePokemons.value.any { it.id == pokemon.id }
    }

    fun addFavorite(pokemon: PokemonDetails) {
        viewModelScope.launch(Dispatchers.IO) {
            pokemonRepository.addFavorite(
                PokemonFavorite(
                    id = pokemon.id,
                    name = pokemon.name,
                    imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${pokemon.id}.png",
                    types = pokemon.types.map { it.type.name },
                    height = pokemon.height,
                    weight = pokemon.weight,
                    stats = pokemon.stats.map { "${it.stat.name}: ${it.base_stat}" }
                )
            )
        }
    }

    fun removeFavorite(pokemon: PokemonDetails) {
        viewModelScope.launch(Dispatchers.IO) {
            pokemonRepository.deleteFavorite(
                PokemonFavorite(
                    id = pokemon.id,
                    name = pokemon.name,
                    imageUrl = "",
                    types = emptyList(),
                    height = 0,
                    weight = 0,
                    stats = emptyList()
                )
            )
        }
    }
}