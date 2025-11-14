package com.example.scoresapp.data.repository

import androidx.compose.runtime.mutableStateListOf
import com.example.scoresapp.data.model.Student
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object StudentRepository {

    private val _students = mutableStateListOf<Student>()
    val students: List<Student> get() = _students


    private val _studentsFlow = MutableStateFlow<List<Student>>(emptyList())
    val studentsFlow: StateFlow<List<Student>> get() = _studentsFlow

    init {

        seed()
    }

    private fun seed() {
        if (_students.isEmpty()) {
            _students.addAll(
                listOf(
                    Student(name = "Ana", lastName = "Pérez", grade = 10, group = "A", score = 8.5),
                    Student(name = "Luis", lastName = "Gómez", grade = 10, group = "B", score = 6.2),
                    Student(name = "María", lastName = "Ruiz", grade = 11, group = "A", score = 9.1),
                    Student(name = "Pedro", lastName = "López", grade = 11, group = "B", score = 5.8),
                    Student(name = "Sofía", lastName = "Jiménez", grade = 10, group = "A", score = 7.3)
                )
            )
            _studentsFlow.value = _students.toList()
        }
    }

    fun addStudent(s: Student) {
        _students.add(s)
        _studentsFlow.value = _students.toList()
    }

    fun updateStudent(updated: Student) {
        val idx = _students.indexOfFirst { it.id == updated.id }
        if (idx >= 0) {
            _students[idx] = updated
            _studentsFlow.value = _students.toList()
        }
    }

    fun removeStudent(id: String) {
        val idx = _students.indexOfFirst { it.id == id }
        if (idx >= 0) {
            _students.removeAt(idx)
            _studentsFlow.value = _students.toList()
        }
    }

    fun getStudent(id: String): Student? {
        return _students.find { it.id == id }
    }



    fun averageByGroup(): Map<String, Double> {
        return _students.groupBy { it.group }
            .mapValues { (_, list) ->
                list.map { it.score }.average()
            }
    }


    fun lowestByGroup(): Map<String, Student?> {
        return _students.groupBy { it.group }
            .mapValues { (_, list) -> list.minByOrNull { it.score } }
    }

    fun topNByGroup(n: Int = 3): Map<String, List<Student>> {
        return _students.groupBy { it.group }
            .mapValues { (_, list) -> list.sortedByDescending { it.score }.take(n) }
    }
}
