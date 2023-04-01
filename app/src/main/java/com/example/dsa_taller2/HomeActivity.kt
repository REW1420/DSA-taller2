package com.example.dsa_taller2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.dsa_taller2.databinding.ActivityHomeBinding
import com.example.dsa_taller2.fragments.SalaryFragment
import com.example.dsa_taller2.fragments.StudentFragment

class HomeActivity : AppCompatActivity() {

    lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(StudentFragment())




        binding.bottomNavigationView.setOnItemSelectedListener {


            when (it.itemId) {


                R.id.salary -> replaceFragment(SalaryFragment())
                R.id.student -> replaceFragment(StudentFragment())

                else -> {


                }

            }

            true

        }


    }


    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }
}