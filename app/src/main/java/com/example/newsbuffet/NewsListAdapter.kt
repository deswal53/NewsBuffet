package com.example.newsbuffet

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Response
import com.bumptech.glide.Glide

class NewsListAdapter(val listener: NewsItemClicked): RecyclerView.Adapter<NewsViewHolder>() {
    private val items: ArrayList<News> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
         val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
         val viewHolder = NewsViewHolder(view)
         view.setOnClickListener{
            listener.onItemClicked(items[viewHolder.adapterPosition])
         }
        return viewHolder
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
         val currentItem = items[position]
         holder.titleView.text = currentItem.title
         holder.author.text = currentItem.author
        Glide.with(holder.itemView.context).load(currentItem.imageUrl).into(holder.image1)
    }

    fun updateNews(updatedNews: ArrayList<News>){
        items.clear()
        items.addAll(updatedNews)

        notifyDataSetChanged()
    }
}

class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    val titleView: TextView = itemView.findViewById(R.id.title)
    val image1: ImageView = itemView.findViewById(R.id.image)
    val author: TextView = itemView.findViewById(R.id.author)
}


interface NewsItemClicked{               // used to take input or functonality need to apply on clickListenrer
   fun onItemClicked(item: News)         // and that functionality is specified in main activity

}