package com.example.dsa_taller2.salary

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.dsa_taller2.R
import com.example.dsa_taller2.databinding.ActivitySalaryDetailBinding
import com.google.firebase.database.FirebaseDatabase

class SalaryDetailActivity : AppCompatActivity() {

    lateinit var binding: ActivitySalaryDetailBinding
    var name: TextView? = null
    var baseSalary: TextView? = null
    var finalSalary: TextView? = null
    var key: String? = null
    var a: Double? = 0.0
    var b: Double? = 0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySalaryDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        name = binding.detailName
        baseSalary = binding.detailSalary
        finalSalary = binding.detailFinalSalary

        val bundle = intent.extras


        if (bundle != null) {
            a = bundle.getDouble("salary")

            binding.detailName.text = "nombre: "+bundle.getString("name")
            binding.detailSalary.text = " salario base: "+ a.toString()
            binding.detailFinalSalary.text = "salario final : "+bundle.getDouble("finalSalary").toString()

            key = bundle.getString("key")


        }


    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.actionDelete -> {
                onDeleteClick()
                true
            }
            R.id.actionEdit -> {
                onEditClick()
                true
            }
            else -> return super.onOptionsItemSelected(item)

        }
    }

    private fun onDeleteClick() {

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Alerta")
            .setMessage("Esta seguroo de eliminar el elemento")
            .setPositiveButton("Aceptar") { _, _ ->
                onDeleteAction()
            }
            .setNegativeButton("Cancelar") { _, _ ->
                return@setNegativeButton
            }
        val alertDialog = builder.create()
        alertDialog.show()

    }

    private fun onDeleteAction() {
        val reference = FirebaseDatabase.getInstance().getReference("salaries")
        reference.child(key!!).removeValue()
        Toast.makeText(this, "Eliminado", Toast.LENGTH_SHORT).show()

    }

    private fun onEditClick() {

        val intent = Intent(this, SalaryUpdateActivity::class.java).apply {
            putExtra("name", name!!.text.toString())
            putExtra("salary", baseSalary!!.text.toString())
            putExtra("key", key)
        }
        startActivity(intent)


    }
}