package com.example.dsa_taller2.student

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.dsa_taller2.controller.StudentController
import com.example.dsa_taller2.databinding.ActivityUploadBinding
import com.example.dsa_taller2.fragments.StudentFragment
import com.google.android.gms.common.api.ApiException

class UploadActivity : AppCompatActivity() {
    lateinit var binding: ActivityUploadBinding
    val UploadController = StudentController()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnUpload.setOnClickListener {

            try {
                UploadController.addStudent(
                    binding.uploadname.text.toString(),
                    binding.uploadnt1.text.toString().toDouble(),
                    binding.uploadnt2.text.toString().toDouble(),
                    binding.uploadnt3.text.toString().toDouble(),
                    binding.uploadnt4.text.toString().toDouble(),
                    binding.uploadnt5.text.toString().toDouble()
                )
            } catch (e: ApiException) {
                Toast.makeText(this, "Ocurrio un error", Toast.LENGTH_SHORT).show()
            }
        }

    }
}