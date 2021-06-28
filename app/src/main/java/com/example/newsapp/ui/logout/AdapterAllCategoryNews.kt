package com.example.newsapp.ui.logout

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.R
import com.example.newsapp.utils.OnClick
import tj.livo.newsapp.models.Articles
import java.util.*

class AdapterAllCategoryNews(
    private val contextAllRecommended: Fragment,
    private val onclick: OnClick,
) :
    RecyclerView.Adapter<AdapterAllCategoryNews.AllCategoryNewsViewHolder>() {

    var sportApiNews: List<Articles> = emptyList()


    fun setData(list: List<tj.livo.newsapp.models.Articles>) {
        sportApiNews = list
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllCategoryNewsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_business_news,
            parent, false
        )
        return AllCategoryNewsViewHolder(itemView)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: AllCategoryNewsViewHolder, position: Int) {
        val currentItem = sportApiNews[position]
        holder.titleTextAllRecommended.text = currentItem.title
        holder.descriptionTextAllRecommended.text = currentItem.description
        Glide.with(contextAllRecommended).load(currentItem.urlToImage).into(holder.titleImage)
        holder.dateAllRecommended.text = getDate(currentItem).toString()
        holder.itemView.setOnClickListener {
            onclick.onclickListener(currentItem)
        }
    }

    override fun getItemCount(): Int {
        return sportApiNews.size
    }

    class AllCategoryNewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleImage: ImageView = itemView.findViewById(R.id.imageAllCategoryNews)
        val titleTextAllRecommended: TextView = itemView.findViewById(R.id.titleTextAllCategoryNews)
        val descriptionTextAllRecommended: TextView =
            itemView.findViewById(R.id.descriptionTextAllCategoryNews)
        val dateAllRecommended: TextView = itemView.findViewById(R.id.dateCategoryNews)
    }


    @SuppressLint("SimpleDateFormat")
    @RequiresApi(Build.VERSION_CODES.N)
    fun getDate(articles: Articles): String? {
        val date: Date = Date()
        val format = "dd.MM.yyyy HH:mm"
        val simpleDateFormat = SimpleDateFormat(format)
        return simpleDateFormat.format(date)
    }
}