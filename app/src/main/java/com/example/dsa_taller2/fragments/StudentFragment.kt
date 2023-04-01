package com.example.dsa_taller2.fragments

import StudentAdapter
import com.example.dsa_taller2.R
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.*
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dsa_taller2.student.UploadActivity
import com.example.dsa_taller2.databinding.FragmentStudentBinding
import com.example.dsa_taller2.model.StudentModel
import com.google.firebase.database.*


class StudentFragment : Fragment() {

    lateinit var binding: FragmentStudentBinding
    lateinit var recyclerView: RecyclerView
    val dataList = mutableListOf<StudentModel>()
    lateinit var adapter: StudentAdapter
    lateinit var eventListener: ValueEventListener
    lateinit var databaseReference: DatabaseReference


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //binding init
        binding = FragmentStudentBinding.inflate(inflater, container, false)

        recyclerView = binding.recyclerView
        val gridLayoutManager = GridLayoutManager(context, 1)
        recyclerView.layoutManager = gridLayoutManager


        val builder = AlertDialog.Builder(context)
        builder.setCancelable(false)
        builder.setView(R.layout.progress)
        val dialog = builder.create()
        dialog.show()


        adapter = StudentAdapter(requireContext(), dataList)
        recyclerView.adapter = adapter

        databaseReference = FirebaseDatabase.getInstance().getReference("students")

        eventListener = databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                dataList.clear()

                for (itemSnapshot in snapshot.children) {
                    val dataClass = itemSnapshot.getValue(StudentModel::class.java)
                    dataClass?.key = itemSnapshot.key
                    dataClass?.let { dataList.add(it) }
                }
                adapter.notifyDataSetChanged()
                dialog.dismiss()
            }

            override fun onCancelled(error: DatabaseError) {
                return
            }
        })



        binding.fab.setOnClickListener {
            val intent = Intent(context, UploadActivity::class.java)
            startActivity(intent)
        }

        // Inflate the layout for this fragment
        return binding.root
    }

}