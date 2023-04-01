package com.example.dsa_taller2.controller

import com.example.dsa_taller2.view.ILoginView
import com.google.firebase.auth.FirebaseAuth

class LoginController(
    private val view: ILoginView
) : ILoginController {

    lateinit var firebaseAuth: FirebaseAuth
    override fun OnLogin(email: String?, password: String?) {
        firebaseAuth = FirebaseAuth.getInstance()

        if (email!!.isNotEmpty() && password!!.isNotEmpty()) {

            firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        view.OnLoginSuccees(true)
                    } else {
                        view.OnLoginError(task.exception.toString())
                        view.OnLoginSuccees(false)
                    }
                }
        } else {
            view.OnLoginError("Campos vacios")
        }
    }
}