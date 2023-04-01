package com.example.dsa_taller2.view

interface ILoginView {

    fun OnLoginSuccees(boolean: Boolean? = false)
    fun OnLoginError(message: String?)
}