import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.dsa_taller2.student.DetailActivity
import com.example.dsa_taller2.R
import com.example.dsa_taller2.model.StudentModel

class StudentAdapter(private val context: Context, private var dataList: List<StudentModel>) :
    RecyclerView.Adapter<StudentAdapter.ItemViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_student, parent, false)
        return ItemViewHolder(view)
    }


    override fun getItemCount(): Int {
        return dataList.size
    }


    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        holder.recName.text = "estudiante: "+dataList[position].name
        holder.recApprove.text = "estado: "+dataList[position].approved
        holder.recAverage.text = "promedio: "+dataList[position].average.toString()

        holder.cardView.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java).apply {
                putExtra("name", dataList[holder.adapterPosition].name)
                putExtra("approve", dataList[holder.adapterPosition].approved)
                putExtra("average", dataList[holder.adapterPosition].average.toString())
                putExtra("note1", dataList[holder.adapterPosition].note1.toString())
                putExtra("note2", dataList[holder.adapterPosition].note2.toString())
                putExtra("note3", dataList[holder.adapterPosition].note3.toString())
                putExtra("note4", dataList[holder.adapterPosition].note4.toString())
                putExtra("note5", dataList[holder.adapterPosition].note5.toString())
                putExtra("key", dataList[holder.adapterPosition].key.toString())
            }

            context.startActivity(intent)
        }


    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val recName: TextView = itemView.findViewById(R.id.recName)
        val recApprove: TextView = itemView.findViewById(R.id.recAprove)
        val recAverage: TextView = itemView.findViewById(R.id.recAverage)
        val cardView: CardView = itemView.findViewById(R.id.recStudent)

    }
}