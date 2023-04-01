package com.example.dsa_taller2.salary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.dsa_taller2.controller.SalaryController
import com.example.dsa_taller2.databinding.ActivitySalaryUpdateBinding
import com.example.dsa_taller2.databinding.ActivitySalaryUploadBinding

class SalaryUploadActivity : AppCompatActivity() {
    lateinit var binding: ActivitySalaryUploadBinding

    val uploadController = SalaryController()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySalaryUploadBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.btnUploadS.setOnClickListener {
            uploadController.addSalary(
                binding.uploadnameS.text.toString(),
                binding.uploadSalary.text.toString().toDouble()
            )
            Toast.makeText(this, "Guardado", Toast.LENGTH_SHORT).show()
        }




    }
}