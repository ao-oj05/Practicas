package com.example.examenprac.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.examenprac.data.datastore.ThemePreference

class ThemeViewModelFactory(private val preference: ThemePreference) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ThemeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ThemeViewModel(preference) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
