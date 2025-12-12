package com.example.examu3.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.examu3.models.PokemonDetails

@Composable
fun PokemonCard(
    pokemon: PokemonDetails,
    isFavorite: Boolean,
    onCardClick: () -> Unit,
    onFavoriteClick: () -> Unit,
    showDeleteButton: Boolean = false
) {
    val typeColor = when (pokemon.types.firstOrNull()?.type?.name) {
        "fire" -> Color(0xFFF08030)
        "water" -> Color(0xFF6890F0)
        "grass" -> Color(0xFF78C850)
        "electric" -> Color(0xFFF8D030)
        "psychic" -> Color(0xFFF85888)
        "ice" -> Color(0xFF98D8D8)
        "dragon" -> Color(0xFF7038F8)
        "dark" -> Color(0xFF705848)
        "fairy" -> Color(0xFFEE99AC)
        "normal" -> Color(0xFFA8A878)
        "fighting" -> Color(0xFFC03028)
        "flying" -> Color(0xFFA890F0)
        "poison" -> Color(0xFFA040A0)
        "ground" -> Color(0xFFE0C068)
        "rock" -> Color(0xFFB8A038)
        "bug" -> Color(0xFFA8B820)
        "ghost" -> Color(0xFF705898)
        "steel" -> Color(0xFFB8B8D0)
        else -> Color.Gray
    }

    val cardBorderColor = Color(0xFFE6C625) 

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { onCardClick() },
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = cardBorderColor) // Yellow border background
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(6.dp) // The "Border" width
        ) {
            Card(
                colors = CardDefaults.cardColors(containerColor = typeColor),
                shape = CardDefaults.shape,
                modifier = Modifier.fillMaxSize()
            ) {
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    if (showDeleteButton) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Eliminar de favoritos",
                            tint = Color.White.copy(alpha = 0.9f),
                            modifier = Modifier
                                .size(28.dp)
                                .padding(4.dp)
                                .align(Alignment.TopEnd)
                                .clickable { onFavoriteClick() }
                        )
                    }

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Start
                        ) {
                             Text(
                                text = pokemon.name.replaceFirstChar { it.uppercase() },
                                color = Color.White,
                                style = androidx.compose.material3.MaterialTheme.typography.titleMedium,
                                modifier = Modifier.padding(bottom = 4.dp, start = 4.dp)
                            )
                        }

                        Card(
                            colors = CardDefaults.cardColors(containerColor = Color(0xFFF0F0F0)),
                            elevation = CardDefaults.cardElevation(2.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(1.2f) // Rectangular image area
                                .padding(vertical = 4.dp)
                        ) {
                             Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                                 AsyncImage(
                                    model = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${pokemon.id}.png",
                                    contentDescription = pokemon.name,
                                    modifier = Modifier.fillMaxSize(0.8f),
                                    contentScale = ContentScale.Fit
                                )
                             }
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Icon(
                            imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                            contentDescription = "Favorito",
                            tint = if (isFavorite) Color.Red else Color.White,
                            modifier = Modifier
                                .size(32.dp)
                                .clickable { onFavoriteClick() }
                        )
                    }
                }
            }
        }
    }
}