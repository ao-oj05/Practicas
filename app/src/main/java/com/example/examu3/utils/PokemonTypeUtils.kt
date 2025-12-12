package com.example.examu3.utils

import com.example.examu3.models.PokemonDetails

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun getTypeColor(typeName: String): Color {
    return when (typeName.lowercase()) {
        "fire" -> Color(0xFFFFB030)
        "water" -> Color(0xFF6B93E8)
        "grass" -> Color(0xFF7AC74C)
        "electric" -> Color(0xFFF7D02C)
        "psychic" -> Color(0xFFF95587)
        "ice" -> Color(0xFF96D9D6)
        "dragon" -> Color(0xFF6F35FC)
        "dark" -> Color(0xFF705746)
        "fairy" -> Color(0xFFD685AD)
        "normal" -> Color(0xFFA8A77A)
        "fighting" -> Color(0xFFC22E28)
        "flying" -> Color(0xFFA98FF3)
        "poison" -> Color(0xFFA33EA1)
        "ground" -> Color(0xFFE2BF65)
        "rock" -> Color(0xFFB6A136)
        "bug" -> Color(0xFFA6B91A)
        "ghost" -> Color(0xFF735797)
        "steel" -> Color(0xFFB7B7CE)
        else -> Color.Gray
    }
}

fun groupPokemonByType(pokemonList: List<PokemonDetails>): Map<String, List<PokemonDetails>> {
    return pokemonList.groupBy { pokemon ->
        pokemon.types.firstOrNull()?.type?.name?.replaceFirstChar { if (it.isLowerCase()) it.uppercase() else it.toString() } ?: "Unknown"
    }.toSortedMap()
}

@Composable
fun getTypesColors(types: List<String>): Map<String, Color> {
    return types.associateWith { typeName ->
        getTypeColor(typeName)
    }
}