package com.example.dsa_taller2.salary

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.dsa_taller2.HomeActivity
import com.example.dsa_taller2.controller.SalaryController
import com.example.dsa_taller2.databinding.ActivitySalaryUpdateBinding
import com.example.dsa_taller2.fragments.SalaryFragment
import com.google.android.gms.common.api.ApiException

class SalaryUpdateActivity : AppCompatActivity() {

    lateinit var binding: ActivitySalaryUpdateBinding
    lateinit var updateName: EditText
    lateinit var updateSalary: EditText
    var name: String? = null
    var salary: Double? = null
    var key: String? = null

    var updateController = SalaryController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySalaryUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        updateName = binding.updatenameS

        updateSalary = binding.updateSalary

        val i = Intent(this, HomeActivity::class.java)

        val bundle = intent.extras
        if (bundle != null) {
            binding.updatenameS.text = Editable.Factory.getInstance().newEditable(bundle.getString("name"))
            binding.updateSalary.text = Editable.Factory.getInstance().newEditable(bundle.getString("salary"))
            key = bundle.getString("key")
        }

        binding.btnUpdateS.setOnClickListener {

            name = updateName.text.toString()
            salary = updateSalary.text.toString().toDouble()

            try {
                updateController.updateSalary(
                    name!!, salary!!, key!!
                )
            } catch (e : ApiException){
                Log.e("MiTag", "Se produjo una ApiException: ${e.message}")
            } finally {
                startActivity(i)
            }




        }
    }
}