package com.example.examu3.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import com.example.examu3.data.local.PokemonFavorite
import com.example.examu3.models.PokemonDetails
import com.example.examu3.models.Type
import com.example.examu3.models.TypeInfo
import com.example.examu3.repository.PokemonRepository

import javax.inject.Inject

@HiltViewModel
class FavoritosViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository
) : ViewModel() {

    val favoritePokemons = pokemonRepository.getFavoritePokemons()
        .map { favorites ->
            favorites.map { favorite ->
                PokemonDetails(
                    id = favorite.id,
                    name = favorite.name,
                    height = 0,
                    weight = 0,
                    types = favorite.types.map { typeName ->
                        Type(slot = 1, type = TypeInfo(name = typeName, url = ""))
                    },
                    stats = emptyList()
                )
            }
        }
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun deleteFavorite(pokemon: PokemonDetails) {
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