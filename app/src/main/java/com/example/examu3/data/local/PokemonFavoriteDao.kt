package com.example.examu3.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonFavoriteDao {

    @Query("SELECT * FROM pokemon_favorites")
    fun getFavoritePokemons(): Flow<List<PokemonFavorite>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavorite(pokemon: PokemonFavorite)

    @Delete
    suspend fun deleteFavorite(pokemon: PokemonFavorite)

    @Query("SELECT * FROM pokemon_favorites WHERE id = :id")
    suspend fun getFavoritePokemonById(id: Int): PokemonFavorite?

    @Query("SELECT * FROM pokemon_favorites WHERE name = :name")
    suspend fun getFavoritePokemonByName(name: String): PokemonFavorite?
}