package com.example.dsa_taller2.controller

import com.example.dsa_taller2.model.StudentModel
import com.google.firebase.database.FirebaseDatabase


class StudentController {


    fun addStudent(
        name: String,
        note1: Double,
        note2: Double,
        note3: Double,
        note4: Double,
        note5: Double,

        ) {
        val database = FirebaseDatabase.getInstance()
        val average = (note1 + note2 + note3 + note4 + note5) / 5.0
        val approved = average >= 6.0
        var finalApproved = ""
        finalApproved = if (approved) {
            "Aprovado"
        } else {
            "Reprovado"
        }

        val student = StudentModel(name, note1, note2, note3, note4, note5, average, finalApproved)
        val ref = database.getReference("students").push()
        ref.setValue(student)
    }

    fun updateStudent(
        name: String,
        note1: Double,
        note2: Double,
        note3: Double,
        note4: Double,
        note5: Double,
        key: String
    ) {
        val database = FirebaseDatabase.getInstance()
        val average = (note1 + note2 + note3 + note4 + note5) / 5.0
        val approved = average >= 6.0
        var finalApproved = ""
        finalApproved = if (approved) {
            "Aprovado"
        } else {
            "Reprovado"
        }

        val updateStudent =
            StudentModel(name, note1, note2, note3, note4, note5, average, finalApproved)
        val ref = database.getReference("students").child(key)
        ref.setValue(updateStudent)


    }

}