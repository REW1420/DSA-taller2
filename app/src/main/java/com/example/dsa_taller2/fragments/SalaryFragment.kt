package com.example.dsa_taller2.fragments


import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dsa_taller2.R
import com.example.dsa_taller2.salary.SalaryUploadActivity
import com.example.dsa_taller2.databinding.FragmentSalaryBinding
import com.example.dsa_taller2.model.SalaryModel
import com.example.dsa_taller2.viewHolder.SalaryAdapter
import com.google.firebase.database.*


class SalaryFragment : Fragment() {

    lateinit var binding: FragmentSalaryBinding
    lateinit var recyclerView: RecyclerView
    val dataListSalary = mutableListOf<SalaryModel>()
    lateinit var adapter: SalaryAdapter
    lateinit var eventListener: ValueEventListener
    lateinit var databaseReference: DatabaseReference


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //binding init
        binding = FragmentSalaryBinding.inflate(inflater, container, false)

        recyclerView = binding.recyclerView
        val gridLayoutManager = GridLayoutManager(context, 1)
        recyclerView.layoutManager = gridLayoutManager

        val builder = AlertDialog.Builder(context)
        builder.setCancelable(false)
        builder.setView(R.layout.progress)
        val dialog = builder.create()
        dialog.show()

        adapter = SalaryAdapter(requireContext(), dataListSalary)
        recyclerView.adapter = adapter

        databaseReference = FirebaseDatabase.getInstance().getReference("salaries")

        eventListener = databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                dataListSalary.clear()

                for (item in snapshot.children) {
                    val dataClass = item.getValue(SalaryModel::class.java)
                    dataClass?.key = item.key
                    dataClass?.let { dataListSalary.add(it) }
                }
                adapter.notifyDataSetChanged()
                dialog.dismiss()
            }

            override fun onCancelled(error: DatabaseError) {
                return
            }

        })

        binding.fabSalary.setOnClickListener {
            val i = Intent(context, SalaryUploadActivity::class.java)
            startActivity(i)
        }

        // Inflate the layout for this fragment
        return binding.root
    }

}