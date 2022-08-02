package com.mugdha.notepadApplication.adapterFiles
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import com.mugdha.notepadApplication.R
import com.mugdha.notepadApplication.activity.MainActivity
import com.mugdha.notepadApplication.activity.UpdateCard
import kotlinx.android.synthetic.main.view.view.*

class Adapter(var data: List<CardInfo>) : RecyclerView.Adapter<Adapter.viewHolder>() {

    class viewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title = itemView.title
        var priority = itemView.priority
        var layout = itemView.mylayout
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.view, parent, false)
        return viewHolder(itemView)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        when (data[position].priority.toLowerCase()) {
            "high" -> holder.layout.setBackgroundColor(Color.parseColor("#CF4526"))
            "medium" -> holder.layout.setBackgroundColor(Color.parseColor("#6DB8C5"))
            else -> holder.layout.setBackgroundColor(Color.parseColor("#F686BD"))
        }
        holder.title.text = data[position].title
        holder.priority.text = data[position].priority
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, UpdateCard::class.java)
            intent.putExtra("id", position)
            holder.itemView.context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return data.size
    }


}