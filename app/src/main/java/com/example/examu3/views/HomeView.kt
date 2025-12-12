package com.example.examu3.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.examu3.viewmodels.HomeViewModel

@Composable
fun HomeView(navController: NavController, viewModel: HomeViewModel = hiltViewModel()) {
    val pokemons by viewModel.pokemons
    val favoritePokemons by viewModel.favoritePokemons.collectAsState()
    val isLoading by viewModel.isLoading
    val error by viewModel.error

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (pokemons.isEmpty() && isLoading) {
            CircularProgressIndicator()
        } else if (pokemons.isEmpty() && error != null) {
            Text(text = error!!, color = Color.Red)
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(8.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                itemsIndexed(pokemons) { index, pokemon ->
                    val isFavorite = favoritePokemons.any { it.id == pokemon.id }
                    PokemonCard(
                        pokemon = pokemon,
                        isFavorite = isFavorite,
                        onCardClick = { navController.navigate("ViewDetails/${pokemon.name}") },
                        onFavoriteClick = {
                            if (isFavorite) {
                                viewModel.removeFavorite(pokemon)
                            } else {
                                viewModel.addFavorite(pokemon)
                            }
                        }
                    )
                    if (index == pokemons.size - 1) {
                        viewModel.getPokemons()
                    }
                }
                if (isLoading && pokemons.isNotEmpty()) {
                    item(span = { GridItemSpan(2) }) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }
                error?.let {
                    if (pokemons.isNotEmpty()) {
                        item(span = { GridItemSpan(2) }) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(text = it, color = Color.Red)
                            }
                        }
                    }
                }
            }
        }
    }
}