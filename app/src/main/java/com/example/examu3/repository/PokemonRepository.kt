package com.example.examu3.repository

import kotlinx.coroutines.flow.Flow
import com.example.examu3.data.local.PokemonFavorite
import com.example.examu3.data.local.PokemonFavoriteDao
import com.example.examu3.data.remote.PokemonApiService
import com.example.examu3.models.PokemonDetails
import com.example.examu3.models.PokemonResponse
import com.example.examu3.models.PokemonResult
import com.example.examu3.models.Stat
import com.example.examu3.models.StatInfo
import com.example.examu3.models.Type
import com.example.examu3.models.TypeInfo

import javax.inject.Inject

class PokemonRepository @Inject constructor(
    private val pokemonApiService: PokemonApiService,
    private val pokemonFavoriteDao: PokemonFavoriteDao
) {

    private var allPokemonsCache: List<PokemonResult>? = null

    suspend fun getPokemons(limit: Int, offset: Int): PokemonResponse {
        return pokemonApiService.getPokemons(limit, offset)
    }

    suspend fun getPokemonDetails(name: String): PokemonDetails {
        val favorite = pokemonFavoriteDao.getFavoritePokemonByName(name)
        if (favorite != null) {
            return PokemonDetails(
                id = favorite.id,
                name = favorite.name,
                height = favorite.height,
                weight = favorite.weight,
                types = favorite.types.map { Type(slot = 1, type = TypeInfo(name = it, url = "")) },
                stats = favorite.stats.map {
                    val parts = it.split(":")
                    Stat(base_stat = parts[1].trim().toInt(), effort = 0, stat = StatInfo(
                        name = parts[0].trim(),
                        url = ""
                    )
                    )
                }
            )
        }
        return pokemonApiService.getPokemonDetails(name)
    }

    suspend fun getAllPokemons(): List<PokemonResult> {
        if (allPokemonsCache == null) {
            allPokemonsCache = pokemonApiService.getPokemons(100, 0).results
        }
        return allPokemonsCache!!
    }

    fun getFavoritePokemons(): Flow<List<PokemonFavorite>> {
        return pokemonFavoriteDao.getFavoritePokemons()
    }

    suspend fun addFavorite(pokemon: PokemonFavorite) {
        pokemonFavoriteDao.addFavorite(pokemon)
    }

    suspend fun deleteFavorite(pokemon: PokemonFavorite) {
        pokemonFavoriteDao.deleteFavorite(pokemon)
    }

    suspend fun isFavorite(id: Int): Boolean {
        return pokemonFavoriteDao.getFavoritePokemonById(id) != null
    }
}