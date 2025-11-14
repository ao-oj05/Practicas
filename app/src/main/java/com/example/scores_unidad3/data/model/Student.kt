package com.example.scoresapp.data.model

import java.util.*

data class Student(
    val id: String = UUID.randomUUID().toString(),
    var name: String,
    var lastName: String,
    var grade: Int,   // por ejemplo: 1..12 u otra escala
    var group: String,
    var score: Double // promedio o nota numérica
)
