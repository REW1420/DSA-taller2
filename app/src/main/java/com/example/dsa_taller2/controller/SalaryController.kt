package com.example.dsa_taller2.controller

import com.example.dsa_taller2.model.SalaryModel
import com.google.firebase.database.FirebaseDatabase
import kotlin.math.roundToInt

class SalaryController {

    fun addSalary(
        name: String,
        salary: Double
    ) {
        val database = FirebaseDatabase.getInstance()
        val finalSalary = (salary * (1 - 0.03 - 0.04 - 0.05)).roundToInt() / 1.0
        val newSalary = SalaryModel(name, salary, finalSalary)

        val ref = database.getReference("salaries").push()
        ref.setValue(newSalary)
    }

    fun updateSalary(
        name: String,
        salary: Double,
        key: String
    ) {
        val database = FirebaseDatabase.getInstance()
        val finalSalary = (salary * (1 - 0.03 - 0.04 - 0.05)).roundToInt() / 100.0
        val newSalary = SalaryModel(name, salary, finalSalary)

        val ref = database.getReference("salaries").child(key)
        ref.setValue(newSalary)
    }
}