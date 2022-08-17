package com.mugdha.notepadApplication.adapterFiles

import android.R
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.icu.lang.UCharacter.GraphemeClusterBreak.V
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.SimpleAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.mugdha.notepadApplication.activity.UpdateCard
import kotlinx.android.synthetic.main.view.view.*


class Adapter(var data: List<CardInfo>) : RecyclerView.Adapter<Adapter.viewHolder>() {
     // private lateinit var binding: ViewBinding
     private val image = "https://cdn.pixabay.com/photo/2018/05/03/21/49/android-3372580_1280.png"
    private val image2 = "http://commondatastorage.googleapis.com/codeskulptor-demos/riceracer_assets/img/tree_2.png"
    class viewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title = itemView.title
        var priority = itemView.priority
        var layout = itemView.mylayout
        var imageView = itemView.imageView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(com.mugdha.notepadApplication.R.layout.view, parent, false)
        return viewHolder(itemView)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        Glide.with(holder.imageView.context)
            .load(image)
            .fitCenter()
            .circleCrop()
            .override(200, 200)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .error(com.mugdha.notepadApplication.R.drawable.ic_launcher_background)
            .placeholder(com.mugdha.notepadApplication.R.drawable.ic_launcher_background)
            .into(holder.imageView)
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