package com.example.dsa_taller2.model

data class StudentModel(
    val name: String = "",
    val note1: Double = 0.0,
    val note2: Double = 0.0,
    val note3: Double = 0.0,
    val note4: Double = 0.0,
    val note5: Double = 0.0,
    val average: Double = 0.0,
    val approved: String = "",
    var key: String? = null
)