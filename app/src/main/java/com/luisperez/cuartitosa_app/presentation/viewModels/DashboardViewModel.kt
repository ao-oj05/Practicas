package com.luisperez.cuartitosa_app.presentation.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.luisperez.cuartitosa_app.data.Student

class DashboardViewModel: ViewModel() {
    var students by mutableStateOf(listOf(
        Student(1L, "Juan", "Portero", "https://i.blogs.es/3c1231/luffy/1200_900.jpeg"),
        Student(2L, "Raul", "Defensa Central", "https://i.redd.it/5k3h3zqxzrlf1.jpeg"),
        Student(3L, "Pedro", "Mediocentro Defensivo", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSdOUesRX9kv550KoFWy9vZ2b3mdgM-GWBVxg&s"),
        Student(4L, "Jose", "Medio punta", "https://static.wikia.nocookie.net/doblaje/images/7/78/OPFilm08_-_Usopp.png/revision/latest?cb=20240827003227&path-prefix=es"),
        Student(5L, "Damian", "Delantero", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQE1ZA7uCwrcmgQzHPTmM89tp80T_Cmwcn8lw&s"),
    ))

    fun addStudent( name: String, description: String, img: String){
        val idStudent: Long = students.last().id+1
        val student: Student = Student(idStudent, name, description,img)
        students =  students + student
    }
}