package com.luisperez.coroutinesappa.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.luisperez.coroutinesappa.viewModels.CoroutinesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ButtonsView(navController: NavController, viewModel: CoroutinesViewModel){
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(
                    text = "Botones"
                )},
                actions ={
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Icon(imageVector = Icons.AutoMirrored.Default.ArrowBack , contentDescription ="" )
                    }
                }
            )
        }

    ) {
        ButtonsViewContent(it, viewModel)
    }
}

@Composable
fun ButtonsViewContent(paddingValues: PaddingValues, viewModel: CoroutinesViewModel){
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Button(
            onClick = {
                viewModel.functionOne()
            }
        ) {
            Text(text = "Funcion 1")
        }

        Spacer(
            modifier = Modifier
                .height(10.dp)
        )

        Button(
            onClick = {
                viewModel.functionTwo()
            }
        ) {
            Text(text = "Funcion 2")
        }

        Spacer(
            modifier = Modifier
                .height(10.dp)
        )

        Button(
            onClick = {
                viewModel.functionThree()
            }
        ) {
            Text(text = "Funcion 3")
        }

        Spacer(
            modifier = Modifier
                .height(20.dp)
        )

        if(viewModel.isLoading.value){
            CircularProgressIndicator()
        } else {
            Text( text = viewModel.result.value)
        }
    }
}