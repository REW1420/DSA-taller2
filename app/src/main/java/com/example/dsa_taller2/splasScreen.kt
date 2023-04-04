package com.example.dsa_taller2

import android.content.Intent
import android.os.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

class splasScreen : AppCompatActivity() {

    lateinit var handler: Handler

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)

        handler = Handler.createAsync(Looper.getMainLooper())

        handler.postDelayed({
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }, 20000)

    }

    override fun onDestroy() {

        handler.removeCallbacksAndMessages(null)
        super.onDestroy()
    }
}
