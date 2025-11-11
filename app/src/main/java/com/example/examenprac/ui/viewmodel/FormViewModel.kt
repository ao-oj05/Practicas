package com.example.examenprac.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.examenprac.data.local.entity.FormEntry
import com.example.examenprac.data.repository.FormRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class FormViewModel(
    private val repository: FormRepository
) : ViewModel() {

    private val _formList = MutableStateFlow<List<FormEntry>>(emptyList())
    val formList: StateFlow<List<FormEntry>> = _formList

    init {
        loadForms()
    }

    fun saveForm(nombre: String, correo: String, edad: Int) {
        val form = FormEntry(
            nombre = nombre,
            correo = correo,
            edad = edad
        )

        viewModelScope.launch {
            repository.insertForm(form)
            loadForms()
        }
    }

    fun loadForms() {
        viewModelScope.launch {
            repository.getAllForms().collectLatest { forms ->
                _formList.value = forms
            }
        }
    }

    // nuevo: eliminar por id
    fun deleteForm(id: Int) {
        viewModelScope.launch {
            repository.deleteForm(id)
            loadForms()
        }
    }
}
