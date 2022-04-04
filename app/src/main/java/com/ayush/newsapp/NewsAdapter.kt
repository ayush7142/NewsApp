package com.ayush.newsapp

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NewsAdapter(val context: Context, val articles: List<Article>):RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {

    inner class ArticleViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.txtTitle)
        val description = itemView.findViewById<TextView>(R.id.txtDescription)
        val container = itemView.findViewById<CardView>(R.id.container)
        val image = itemView.findViewById<ImageView>(R.id.image)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_view, parent,false)
        return ArticleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        // bind data with views
        holder.title.text = articles[position].title
        holder.description.text = articles[position].description
        Glide.with(context).load(articles[position].urlToImage).into(holder.image)  // imageView are binded with glide library

        var color = "#EEEEEE"
        if(position % 2 == 0) {
            color = "#FFFFFF"
        }
        holder.container.setBackgroundColor(Color.parseColor(color))
        // click event
        holder.itemView.setOnClickListener {
            val intent = Intent(context, WebView::class.java)
            intent.putExtra("url", articles[position].url)
            context.startActivity(intent)
        }
    }

        override fun getItemCount(): Int {
            return articles.size
        }
    }


