package com.example.examenprac.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "form_entries")
data class FormEntry(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nombre: String,
    val correo: String,
    val edad: Int
)
