package com.example.dsa_taller2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.dsa_taller2.controller.ISingupController
import com.example.dsa_taller2.controller.SingupController
import com.example.dsa_taller2.databinding.ActivitySingupBinding
import com.example.dsa_taller2.view.ISingupView

class SingupActivity : AppCompatActivity(), ISingupView {

    lateinit var  binding: ActivitySingupBinding

    var singupPresenter : ISingupController?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySingupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //controller instance
        singupPresenter = SingupController(this)



        binding.btnSingUp.setOnClickListener {
            (singupPresenter as SingupController).OnSingup(
                binding.etxtEmail.text.toString(),
                binding.etxtPassword.text.toString(),
                binding.etxtPasswordConfirm.text.toString()
            )
        }
    }

    override fun OnSigupSuccees(boolean: Boolean?) {
       if (boolean == true){
           val intent = Intent(this, LoginActivity::class.java)
           startActivity(intent)
       }
    }

    override fun OnSingupError(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}