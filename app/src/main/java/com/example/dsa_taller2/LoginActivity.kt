package com.example.dsa_taller2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.dsa_taller2.controller.ILoginController
import com.example.dsa_taller2.controller.LoginController
import com.example.dsa_taller2.databinding.ActivityLoginBinding
import com.example.dsa_taller2.view.ILoginView

class LoginActivity : AppCompatActivity(),ILoginView {

    lateinit var binding: ActivityLoginBinding

    var loginPresenter : ILoginController?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //controller instances
        loginPresenter = LoginController(this)

        binding.btnLogIn.setOnClickListener {
            (loginPresenter as LoginController).OnLogin(
                binding.etxtEmail.text.toString(),
                binding.etxtPassword.text.toString()
            )
        }


        binding.txtSingUp.setOnClickListener {
         val intent = Intent(this, SingupActivity::class.java)
            startActivity(intent)
        }

    }

    override fun OnLoginSuccees(boolean: Boolean?) {
        if (boolean == true){
            val intent = Intent(this,HomeActivity::class.java)
            startActivity(intent)
        } else {
            Toast.makeText(this, "A ocurrido un error al iniciar sesion", Toast.LENGTH_SHORT).show()
        }
    }

    override fun OnLoginError(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}