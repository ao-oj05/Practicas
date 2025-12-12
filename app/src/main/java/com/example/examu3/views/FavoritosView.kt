package com.example.examu3.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox
import com.example.examu3.models.PokemonDetails
import com.example.examu3.viewmodels.FavoritosViewModel

@Composable
fun FavoritosView(navController: NavController, viewModel: FavoritosViewModel = hiltViewModel()) {
    val favoritePokemons by viewModel.favoritePokemons.collectAsState()
    var showDialog by remember { mutableStateOf(false) }
    var pokemonToDelete by remember { mutableStateOf<PokemonDetails?>(null) }

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(favoritePokemons) { pokemon ->
            val deleteAction = SwipeAction(
                onSwipe = {
                    pokemonToDelete = pokemon
                    showDialog = true
                },
                icon = {
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .background(Color.Red)
                            .padding(16.dp),
                        contentAlignment = Alignment.CenterEnd
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.End
                        ) {
                            Text(
                                text = "Eliminar",
                                color = Color.White,
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier.padding(end = 8.dp)
                            )
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Eliminar de favoritos",
                                tint = Color.White,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }
                },
                background = Color.Red,
                isUndo = false
            )

            SwipeableActionsBox(
                endActions = listOf(deleteAction),
                swipeThreshold = 100.dp
            ) {
                PokemonCard(
                    pokemon = pokemon,
                    isFavorite = true,
                    onCardClick = { navController.navigate("ViewDetails/${pokemon.name}") },
                    onFavoriteClick = {
                        pokemonToDelete = pokemon
                        showDialog = true
                    },
                    showDeleteButton = true
                )
            }
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = {
                Text(
                    text = "Eliminar de favoritos",
                    style = MaterialTheme.typography.headlineSmall
                )
            },
            text = {
                Text(
                    text = "¿Estás seguro de que deseas eliminar a ${pokemonToDelete?.name?.replaceFirstChar { it.uppercase() }} de tus favoritos?",
                    style = MaterialTheme.typography.bodyLarge
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        pokemonToDelete?.let { viewModel.deleteFavorite(it) }
                        showDialog = false
                    }
                ) {
                    Text(
                        text = "Eliminar",
                        color = MaterialTheme.colorScheme.error
                    )
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showDialog = false
                    }
                ) {
                    Text("Cancelar")
                }
            }
        )
    }
}