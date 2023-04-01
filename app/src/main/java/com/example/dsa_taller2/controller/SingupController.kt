package com.example.dsa_taller2.controller

import com.example.dsa_taller2.view.ISingupView
import com.google.firebase.auth.FirebaseAuth

class SingupController(
    private val view: ISingupView
) : ISingupController {

    lateinit var firebaseAuth: FirebaseAuth


    override fun OnSingup(email: String?, password: String?, confirmPassword: String?) {

        firebaseAuth = FirebaseAuth.getInstance()

        if (email!!.isNotEmpty() && password!!.isNotEmpty() && confirmPassword!!.isNotEmpty()) {
            if (email.isNotEmpty() || password.isNotEmpty() || confirmPassword.isNotEmpty()) {
                if (confirmPassword == password) {
                    firebaseAuth.createUserWithEmailAndPassword(email, confirmPassword).addOnCompleteListener {
                        task ->
                        if (task.isSuccessful){
                            view.OnSigupSuccees(true)
                        } else {
                            view.OnSingupError(task.exception.toString())

                        }
                    }
                } else {
                    view.OnSingupError("Las contraseñas no coinciden")
                }
            } else {
                view.OnSingupError("Los campos estan vacios")
            }
        } else {
            view.OnSingupError("Los campos estan vacios")
        }
    }
}