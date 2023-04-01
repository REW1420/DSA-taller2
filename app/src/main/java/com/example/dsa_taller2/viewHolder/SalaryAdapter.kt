package com.example.dsa_taller2.viewHolder

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.dsa_taller2.R
import com.example.dsa_taller2.salary.SalaryDetailActivity
import com.example.dsa_taller2.model.SalaryModel

class SalaryAdapter(val context: Context, private var dataList: List<SalaryModel>) :
    RecyclerView.Adapter<SalaryAdapter.ItemViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_salary, parent, false)
        return ItemViewHolder(view)
    }


    override fun onBindViewHolder(holder: SalaryAdapter.ItemViewHolder, position: Int) {
        holder.recName.text = "nombre "+dataList[holder.adapterPosition].name
        holder.recSalary.text = "salario final"+dataList[holder.adapterPosition].finalSalary.toString()

        holder.cardView.setOnClickListener {
            val intent = Intent(context, SalaryDetailActivity::class.java).apply {
                putExtra("name", dataList[holder.adapterPosition].name)
                putExtra("salary", dataList[holder.adapterPosition].baseSalary)
                putExtra("finalSalary", dataList[holder.adapterPosition].finalSalary)
                putExtra("key", dataList[holder.adapterPosition].key)
            }

            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }


    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val recName: TextView = itemView.findViewById(R.id.recNameS)
        val recSalary: TextView = itemView.findViewById(R.id.recFinalSalary)
        val cardView: CardView = itemView.findViewById(R.id.recSalary)

    }

}