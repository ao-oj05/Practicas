package com.example.examu3.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.examu3.viewmodels.ViewDetailsViewModel

@Composable
fun ViewDetails(pokemonName: String, navController: NavController, viewModel: ViewDetailsViewModel = hiltViewModel()) {
    val pokemonDetails by viewModel.pokemonDetails
    val isFavorite by viewModel.isFavorite
    val isLoading by viewModel.isLoading
    val error by viewModel.error

    LaunchedEffect(pokemonName) {
        viewModel.getPokemonDetails(pokemonName)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        IconButton(onClick = { navController.popBackStack() }) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
        }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            if (isLoading) {
                CircularProgressIndicator()
            } else if (error != null) {
                Text(text = error!!, color = Color.Red)
            } else {
                pokemonDetails?.let { details ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState()),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        AsyncImage(
                            model = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${details.id}.png",
                            contentDescription = details.name,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(300.dp),
                            contentScale = ContentScale.Fit
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(text = details.name.replaceFirstChar { it.uppercase() }, fontSize = 24.sp)
                            Spacer(modifier = Modifier.weight(1f))
                            Icon(
                                imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                                contentDescription = "Favorite",
                                tint = if (isFavorite) Color.Red else Color.Gray,
                                modifier = Modifier
                                    .size(32.dp)
                                    .clickable { viewModel.toggleFavorite() }
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(text = "Height: ${details.height / 10.0} m")
                        Text(text = "Weight: ${details.weight / 10.0} kg")
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(text = "Stats:", fontSize = 20.sp)
                        details.stats.forEach {
                            Text(text = "${it.stat.name.replaceFirstChar { char -> char.uppercase() }}: ${it.base_stat}")
                        }
                    }
                }
            }
        }
    }
}