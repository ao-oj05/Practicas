package com.example.examenprac.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.examenprac.data.datastore.ThemePreference
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ThemeViewModel(private val preference: ThemePreference) : ViewModel() {

    val isDarkTheme = preference.isDarkTheme.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = false
    )

    fun toggleTheme(isDarkMode: Boolean) {
        viewModelScope.launch {
            preference.saveThemePreference(isDarkMode)
        }
    }
}
