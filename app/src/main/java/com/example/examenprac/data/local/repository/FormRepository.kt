package com.example.examenprac.data.repository

import com.example.examenprac.data.local.dao.FormDao
import com.example.examenprac.data.local.entity.FormEntry
import kotlinx.coroutines.flow.Flow

class FormRepository(private val formDao: FormDao) {

    suspend fun insertForm(form: FormEntry) {
        formDao.insertForm(form)
    }

    fun getAllForms(): Flow<List<FormEntry>> {
        return formDao.getAllForms()
    }

    suspend fun deleteForm(id: Int) {
        formDao.deleteById(id)
    }
}
