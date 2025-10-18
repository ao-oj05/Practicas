package com.luisperez.cuartitosa_app.presentation.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.R
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.luisperez.cuartitosa_app.data.Student
import com.luisperez.cuartitosa_app.presentation.viewModels.DashboardViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsView(navController: NavController, id: Long, dashboardViewModel: DashboardViewModel) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("Detalle del estudiante")
                },
                actions = {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Icon(imageVector = Icons.AutoMirrored.Default.ArrowBack, contentDescription = "")
                    }
                }
            )
        }
    ) {
        DetailsContent(it, id, dashboardViewModel)
    }
}

@Composable
fun DetailsContent(paddingValues: PaddingValues, id: Long, dashboardViewModel: DashboardViewModel){
    val student: Student? = dashboardViewModel.students.getOrNull(id.toInt()-1)

    if(student !== null){
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally

        ){
            Spacer(modifier = Modifier
                .padding(10.dp))
            Text("Nombre:", fontWeight = FontWeight.Bold)
            Text( student.name)
            Text("Descripcion:", fontWeight = FontWeight.Bold)
            Text(student.description)
            AsyncImage(
                model = student.image,
                contentDescription = "Imagen de ${student.name}",
                modifier = Modifier
                    .height(300.dp)
                    .fillMaxWidth()
                    .padding(top = 10.dp)

            )
        }
    } else {
        Text("Personaje no encontrado")
    }

}