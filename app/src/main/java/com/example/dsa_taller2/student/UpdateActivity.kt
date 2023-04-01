package com.example.dsa_taller2.student

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.dsa_taller2.HomeActivity
import com.example.dsa_taller2.controller.StudentController
import com.example.dsa_taller2.databinding.ActivityUpdateBinding
import com.google.android.gms.common.api.ApiException

class UpdateActivity : AppCompatActivity() {

    lateinit var binding: ActivityUpdateBinding

    lateinit var updateName: EditText
    lateinit var updateNote1: EditText
    lateinit var updateNote2: EditText
    lateinit var updateNote3: EditText
    lateinit var updateNote4: EditText
    lateinit var updateNote5: EditText
    val updateController = StudentController()
    var name: String? = null
    var note1: Double? = null
    var note2: Double? = null
    var note3: Double? = null
    var note4: Double? = null
    var note5: Double? = null
    var key: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val i = Intent(this, HomeActivity::class.java)

        val bundle = intent.extras
        if (bundle != null) {
            binding.updatename.text =
                Editable.Factory.getInstance().newEditable(bundle.getString("name").toString())
            binding.updatent1.text =
                Editable.Factory.getInstance().newEditable(bundle.getString("note1"))
            binding.updatent2.text =
                Editable.Factory.getInstance().newEditable(bundle.getString("note2"))
            binding.updatent3.text =
                Editable.Factory.getInstance().newEditable(bundle.getString("note3"))
            binding.updatent4.text =
                Editable.Factory.getInstance().newEditable(bundle.getString("note4"))
            binding.updatent5.text =
                Editable.Factory.getInstance().newEditable(bundle.getString("note5"))
            key = bundle.getString("key")

        }

        binding.btnUpdate.setOnClickListener {

            name = updateName.text.toString()
            note1 = updateNote1.text.toString().toDouble()
            note2 = updateNote2.text.toString().toDouble()
            note3 = updateNote3.text.toString().toDouble()
            note4 = updateNote4.text.toString().toDouble()
            note5 = updateNote5.text.toString().toDouble()
            try {
                updateController.updateStudent(
                    name!!, note1!!, note2!!, note3!!, note4!!, note5!!, key!!
                )

            } catch (e: ApiException) {
                Log.e("MiTag", "Se produjo una ApiException: ${e.message}")
            } finally {
                startActivity(i)
            }


        }

    }


}

