package com.luisperez.cuartitosa_app.presentation.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.luisperez.cuartitosa_app.data.Student
import com.luisperez.cuartitosa_app.presentation.viewModels.DashboardViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardView(navController: NavController, dashboardViewModel: DashboardViewModel){
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Dashboard") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate("AddStudent")
                }
            ) {
                Icon(Icons.Default.Add, contentDescription = "")
            }
        }
    ) {
        Content(it, dashboardViewModel.students, navController)
    }

}

@Composable
fun Content(paddingValues: PaddingValues, students: List<Student>, navController: NavController){
    LazyColumn(
        modifier = Modifier
            .padding(paddingValues).padding(horizontal = 10.dp)
    ) {
        items(students){ student ->
            Box(
                modifier = Modifier
                    .clickable{
                        navController.navigate("Details/${student.id}")
                    }
                    .size(90.dp)
                    .background(color = Color.White, CircleShape)
                    .wrapContentSize(Alignment.Center)
                    .padding(10.dp)
            ){
                Column{
                    Text(student.name, color = Color.Black)
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
        }
    }

}

@Composable
fun AddMenu(){

}