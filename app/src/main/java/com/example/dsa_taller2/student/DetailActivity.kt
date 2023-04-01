package com.example.dsa_taller2.student

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.dsa_taller2.R
import com.example.dsa_taller2.databinding.ActivityDetailBinding
import com.example.dsa_taller2.fragments.StudentFragment
import com.google.firebase.database.FirebaseDatabase

class DetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailBinding

    var name: TextView? = null
    var note1: TextView? = null
    var note2: TextView? = null
    var note3: TextView? = null
    var note4: TextView? = null
    var note5: TextView? = null
    var approve: TextView? = null
    var average: TextView? = null
    var key: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        name = binding.dateilname
        note1 = binding.dateilnt1
        note2 = binding.dateilnt2
        note3 = binding.dateilnt3
        note4 = binding.dateilnt4
        note5 = binding.dateilnt5
        approve = binding.approve
        average = binding.average

        val bundle = intent.extras
        if (bundle != null) {
            name!!.text = "nombre: "+bundle.getString("name").toString()
            note1!!.text = "nota 1: "+bundle.getString("note1")?.toDouble().toString()
            note2!!.text = "nota 2: "+bundle.getString("note2")
            note3!!.text = "nota 3: "+bundle.getString("note3").toString()
            note4!!.text = "nota 4: "+bundle.getString("note4").toString()
            note5!!.text = "nota 5: "+bundle.getString("note5").toString()
            approve!!.text = "estado: "+bundle.getString("approve").toString()
            average!!.text = "promedio: "+bundle.getString("average").toString()
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
        val reference = FirebaseDatabase.getInstance().getReference("students")
        reference.child(key!!).removeValue()
        Toast.makeText(this, "Eliminado", Toast.LENGTH_SHORT).show()

    }

    private fun onEditClick() {
        val intent = Intent(this, UpdateActivity::class.java).apply {
            putExtra("name", name!!.text.toString())
            putExtra("note1", note1!!.text.toString())
            putExtra("note2", note2!!.text.toString())
            putExtra("note3", note3!!.text.toString())
            putExtra("note4", note4!!.text.toString())
            putExtra("note5", note5!!.text.toString())
            putExtra("key", key)
        }

        startActivity(intent)
    }
}